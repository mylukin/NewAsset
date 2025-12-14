package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.dto.AssetMaintOrderCreateDTO;
import com.ruoyi.asset.domain.entity.AssetMaintOrder;
import com.ruoyi.asset.domain.vo.AssetMaintLogVO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderDetailVO;
import com.ruoyi.asset.domain.vo.AssetMaintOrderListVO;
import com.ruoyi.asset.mapper.AssetMaintLogMapper;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper;
import com.ruoyi.asset.mapper.AssetMaintOrderMapper.MaintOrderQuery;
import com.ruoyi.asset.service.IAssetMaintOrderService;
import com.ruoyi.asset.service.rule.MaintOrderWorkflowService;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maintenance Order Service Implementation
 */
@Service
public class AssetMaintOrderServiceImpl implements IAssetMaintOrderService {

    @Autowired
    private AssetMaintOrderMapper maintOrderMapper;

    @Autowired
    private AssetMaintLogMapper maintLogMapper;

    @Autowired
    private MaintOrderWorkflowService workflowService;

    @Override
    @DataScope(projectAlias = "mo", deptAlias = "mo", userAlias = "mo")
    public List<AssetMaintOrderListVO> selectMaintOrderList(MaintOrderQuery query) {
        return maintOrderMapper.selectMaintOrderList(query);
    }

    @Override
    public AssetMaintOrderDetailVO selectMaintOrderById(Long id) {
        AssetMaintOrderDetailVO detail = maintOrderMapper.selectMaintOrderById(id);
        if (detail != null) {
            // Include order logs
            List<AssetMaintLogVO> logs = maintLogMapper.selectLogsByOrderId(id);
            // Convert to MaintLogVO list for detail view
            List<AssetMaintOrderDetailVO.MaintLogVO> detailLogs = logs.stream()
                    .map(this::convertToDetailLog)
                    .collect(Collectors.toList());
            detail.setMaintLogs(detailLogs);
        }
        return detail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createMaintOrder(AssetMaintOrderCreateDTO createDTO) {
        Long operatorId = getCurrentUserId();

        AssetMaintOrder order = new AssetMaintOrder();
        order.setAssetId(createDTO.getAssetId());
        order.setProjectId(createDTO.getProjectId());
        order.setOrderType(createDTO.getOrderType());
        order.setFaultType(createDTO.getFaultType());
        order.setTitle(createDTO.getTitle());
        order.setDescription(createDTO.getDescription());
        order.setPriority(createDTO.getPriority() != null ? createDTO.getPriority() : "NORMAL");
        order.setRequireFinishTime(createDTO.getRequireFinishTime());

        AssetMaintOrder created = workflowService.createOrder(order, operatorId);

        // If handler is specified, assign immediately
        if (createDTO.getHandlerId() != null) {
            workflowService.assignOrder(created.getId(), createDTO.getHandlerId(), operatorId, "Initial assignment");
        }

        return created.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int assignOrder(Long orderId, Long handlerId, String remark) {
        Long operatorId = getCurrentUserId();
        workflowService.assignOrder(orderId, handlerId, operatorId, remark);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int acceptOrder(Long orderId, String remark) {
        Long operatorId = getCurrentUserId();
        workflowService.acceptOrder(orderId, operatorId, remark);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int rejectOrder(Long orderId, String reason) {
        Long operatorId = getCurrentUserId();
        workflowService.rejectOrder(orderId, operatorId, reason);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int completeOrder(Long orderId, String result) {
        Long operatorId = getCurrentUserId();
        workflowService.completeOrder(orderId, operatorId, result);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int confirmAndClose(Long orderId, String feedback) {
        Long operatorId = getCurrentUserId();
        workflowService.confirmAndClose(orderId, operatorId, feedback);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelOrder(Long orderId, String reason) {
        Long operatorId = getCurrentUserId();
        workflowService.cancelOrder(orderId, operatorId, reason);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMaintOrderById(Long id) {
        // Delete logs first (foreign key constraint)
        maintLogMapper.deleteLogsByOrderId(id);
        return maintOrderMapper.deleteMaintOrderById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMaintOrderByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            maintLogMapper.deleteLogsByOrderId(id);
            count += maintOrderMapper.deleteMaintOrderById(id);
        }
        return count;
    }

    @Override
    public int countByStatus(Long projectId, String status) {
        return maintOrderMapper.countByStatus(projectId, status);
    }

    @Override
    public List<AssetMaintOrderListVO> getOrdersByAssetId(Long assetId) {
        MaintOrderQuery query = new MaintOrderQuery();
        query.setAssetId(assetId);
        return maintOrderMapper.selectMaintOrderList(query);
    }

    // Helper methods

    private Long getCurrentUserId() {
        try {
            return SecurityUtils.getUserId();
        } catch (Exception e) {
            // Default to system user if security context not available
            return 1L;
        }
    }

    private AssetMaintOrderDetailVO.MaintLogVO convertToDetailLog(AssetMaintLogVO log) {
        AssetMaintOrderDetailVO.MaintLogVO detailLog = new AssetMaintOrderDetailVO.MaintLogVO();
        detailLog.setId(log.getId());
        detailLog.setFromStatus(log.getFromStatus());
        detailLog.setFromStatusLabel(log.getFromStatusLabel());
        detailLog.setToStatus(log.getToStatus());
        detailLog.setToStatusLabel(log.getToStatusLabel());
        detailLog.setOperatorId(log.getOperatorId());
        detailLog.setOperatorName(log.getOperatorName());
        detailLog.setOperationType(log.getOperationType());
        detailLog.setOperationTypeLabel(log.getOperationTypeLabel());
        detailLog.setContent(log.getContent());
        detailLog.setOpTime(log.getOpTime());
        return detailLog;
    }
}
