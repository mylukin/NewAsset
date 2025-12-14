package com.ruoyi.asset.service.rule;

import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetMaintLog;
import com.ruoyi.asset.domain.entity.AssetMaintOrder;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.domain.vo.AssetMaintOrderDetailVO;
import com.ruoyi.asset.mapper.AssetMaintLogMapper;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper;
import com.ruoyi.asset.mapper.AssetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Maintenance Order Workflow Service Implementation
 */
@Service
public class MaintOrderWorkflowServiceImpl implements MaintOrderWorkflowService {

    // Transition map: from_status -> allowed to_statuses
    private static final Map<String, Set<String>> VALID_TRANSITIONS = new HashMap<>();

    static {
        // Define valid transitions
        VALID_TRANSITIONS.put(STATUS_WAIT_ASSIGN, new HashSet<>(Arrays.asList(STATUS_WAIT_ACCEPT, STATUS_CANCELED)));
        VALID_TRANSITIONS.put(STATUS_WAIT_ACCEPT, new HashSet<>(Arrays.asList(STATUS_PROCESSING, STATUS_WAIT_ASSIGN, STATUS_CANCELED)));
        VALID_TRANSITIONS.put(STATUS_PROCESSING, new HashSet<>(Collections.singletonList(STATUS_WAIT_CONFIRM)));
        VALID_TRANSITIONS.put(STATUS_WAIT_CONFIRM, new HashSet<>(Arrays.asList(STATUS_CLOSED, STATUS_PROCESSING)));
        VALID_TRANSITIONS.put(STATUS_CLOSED, new HashSet<>());
        VALID_TRANSITIONS.put(STATUS_CANCELED, new HashSet<>());
    }

    @Autowired
    private AssetMaintOrderMapper maintOrderMapper;

    @Autowired
    private AssetMaintLogMapper maintLogMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetStatusService assetStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder createOrder(AssetMaintOrder order, Long operatorId) {
        // Generate order number
        order.setOrderNo(generateOrderNo());
        order.setStatus(STATUS_WAIT_ASSIGN);
        order.setRequesterId(operatorId);
        order.setCreateTime(new Date());

        // Get and store original asset status
        Asset asset = assetMapper.selectById(order.getAssetId());
        if (asset != null) {
            order.setOriginAssetStatus(asset.getStatus());
        }

        maintOrderMapper.insertMaintOrder(order);

        // Log the creation
        logOperation(order.getId(), null, STATUS_WAIT_ASSIGN, operatorId, OP_CREATE, "Order created");

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder assignOrder(Long orderId, Long handlerId, Long operatorId, String remark) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_WAIT_ASSIGN);

        String fromStatus = order.getStatus();
        order.setCurrentHandlerId(handlerId);
        order.setStatus(STATUS_WAIT_ACCEPT);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_WAIT_ACCEPT, operatorId, OP_ASSIGN, remark);

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder acceptOrder(Long orderId, Long operatorId, String remark) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_WAIT_ACCEPT);

        String fromStatus = order.getStatus();
        order.setStatus(STATUS_PROCESSING);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_PROCESSING, operatorId, OP_ACCEPT, remark);

        // Change asset status to MAINTAINING
        assetStatusService.changeStatusByMaintenance(
                order.getAssetId(),
                AssetStatusEnum.MAINTAINING,
                orderId,
                operatorId
        );

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder rejectOrder(Long orderId, Long operatorId, String reason) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_WAIT_ACCEPT);

        String fromStatus = order.getStatus();
        order.setCurrentHandlerId(null);
        order.setStatus(STATUS_WAIT_ASSIGN);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_WAIT_ASSIGN, operatorId, OP_REJECT, reason);

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder startProcessing(Long orderId, Long operatorId, String remark) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_PROCESSING);

        // Log start without status change
        logOperation(orderId, STATUS_PROCESSING, STATUS_PROCESSING, operatorId, OP_START, remark);

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder completeOrder(Long orderId, Long operatorId, String result) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_PROCESSING);

        String fromStatus = order.getStatus();
        order.setStatus(STATUS_WAIT_CONFIRM);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_WAIT_CONFIRM, operatorId, OP_COMPLETE, result);

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder confirmAndClose(Long orderId, Long operatorId, String feedback) {
        AssetMaintOrder order = getOrderAndValidate(orderId, STATUS_WAIT_CONFIRM);

        String fromStatus = order.getStatus();
        order.setStatus(STATUS_CLOSED);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_CLOSED, operatorId, OP_CONFIRM, feedback);

        // Restore asset status to original
        if (order.getOriginAssetStatus() != null) {
            assetStatusService.restoreStatusAfterMaintenance(
                    order.getAssetId(),
                    orderId,
                    order.getOriginAssetStatus(),
                    operatorId
            );
        }

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetMaintOrder cancelOrder(Long orderId, Long operatorId, String reason) {
        AssetMaintOrder order = getOrderById(orderId);

        // Can only cancel if WAIT_ASSIGN or WAIT_ACCEPT
        if (!STATUS_WAIT_ASSIGN.equals(order.getStatus()) && !STATUS_WAIT_ACCEPT.equals(order.getStatus())) {
            throw new IllegalStateException("Cannot cancel order in status: " + order.getStatus());
        }

        String fromStatus = order.getStatus();
        order.setStatus(STATUS_CANCELED);
        order.setUpdateTime(new Date());

        maintOrderMapper.updateMaintOrder(order);
        logOperation(orderId, fromStatus, STATUS_CANCELED, operatorId, OP_CANCEL, reason);

        return order;
    }

    @Override
    public boolean isValidTransition(String fromStatus, String toStatus) {
        Set<String> validTargets = VALID_TRANSITIONS.get(fromStatus);
        return validTargets != null && validTargets.contains(toStatus);
    }

    @Override
    public Set<String> getValidNextStatuses(String currentStatus) {
        return VALID_TRANSITIONS.getOrDefault(currentStatus, Collections.emptySet());
    }

    // Helper methods

    private AssetMaintOrder getOrderById(Long orderId) {
        AssetMaintOrder order = new AssetMaintOrder();
        order.setId(orderId);
        // In real implementation, fetch from mapper
        // For now, we'll use the detail VO and convert
        AssetMaintOrderDetailVO detail = maintOrderMapper.selectMaintOrderById(orderId);
        if (detail == null) {
            throw new RuntimeException("Maintenance order not found: " + orderId);
        }
        order.setId(detail.getId());
        order.setOrderNo(detail.getOrderNo());
        order.setAssetId(detail.getAssetId());
        order.setProjectId(detail.getProjectId());
        order.setOrderType(detail.getOrderType());
        order.setFaultType(detail.getFaultType());
        order.setTitle(detail.getTitle());
        order.setDescription(detail.getDescription());
        order.setPriority(detail.getPriority());
        order.setRequireFinishTime(detail.getRequireFinishTime());
        order.setStatus(detail.getStatus());
        order.setOriginAssetStatus(detail.getOriginAssetStatus());
        order.setCurrentHandlerId(detail.getCurrentHandlerId());
        order.setRequesterId(detail.getRequesterId());
        return order;
    }

    private AssetMaintOrder getOrderAndValidate(Long orderId, String expectedStatus) {
        AssetMaintOrder order = getOrderById(orderId);
        if (!expectedStatus.equals(order.getStatus())) {
            throw new IllegalStateException(
                    "Invalid operation. Order status is " + order.getStatus() + ", expected " + expectedStatus
            );
        }
        return order;
    }

    private void logOperation(Long orderId, String fromStatus, String toStatus, Long operatorId, String operationType, String content) {
        AssetMaintLog log = new AssetMaintLog();
        log.setOrderId(orderId);
        log.setFromStatus(fromStatus);
        log.setToStatus(toStatus);
        log.setOperatorId(operatorId);
        log.setOperationType(operationType);
        log.setContent(content);
        log.setOpTime(new Date());
        maintLogMapper.insertMaintLog(log);
    }

    private String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        int random = new Random().nextInt(9000) + 1000;
        return "MO" + timestamp + random;
    }
}
