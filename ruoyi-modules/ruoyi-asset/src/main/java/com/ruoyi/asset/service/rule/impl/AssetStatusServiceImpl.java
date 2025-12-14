package com.ruoyi.asset.service.rule.impl;

import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetStatusLog;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.exception.InvalidStateTransitionException;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.mapper.AssetStatusLogMapper;
import com.ruoyi.asset.service.rule.AssetStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Asset status state machine service implementation
 * Defines valid state transitions and handles status changes
 */
@Service
public class AssetStatusServiceImpl implements AssetStatusService {

    private static final Logger log = LoggerFactory.getLogger(AssetStatusServiceImpl.class);

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetStatusLogMapper statusLogMapper;

    /**
     * State transition map: from status -> set of valid to statuses
     */
    private static final Map<AssetStatusEnum, Set<AssetStatusEnum>> TRANSITION_MAP = new HashMap<>();

    static {
        // UNDER_CONSTRUCTION can go to any available state
        TRANSITION_MAP.put(AssetStatusEnum.UNDER_CONSTRUCTION, EnumSet.of(
                AssetStatusEnum.AVAILABLE_SELF,
                AssetStatusEnum.AVAILABLE_RENT,
                AssetStatusEnum.AVAILABLE_IDLE
        ));

        // AVAILABLE_SELF can go to rent, idle, closed, fault, maintaining, or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.AVAILABLE_SELF, EnumSet.of(
                AssetStatusEnum.AVAILABLE_RENT,
                AssetStatusEnum.AVAILABLE_IDLE,
                AssetStatusEnum.TEMP_CLOSED,
                AssetStatusEnum.FAULT,
                AssetStatusEnum.MAINTAINING,
                AssetStatusEnum.TO_BE_SCRAPPED
        ));

        // AVAILABLE_RENT can go to self, idle, closed, fault, maintaining, or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.AVAILABLE_RENT, EnumSet.of(
                AssetStatusEnum.AVAILABLE_SELF,
                AssetStatusEnum.AVAILABLE_IDLE,
                AssetStatusEnum.TEMP_CLOSED,
                AssetStatusEnum.FAULT,
                AssetStatusEnum.MAINTAINING,
                AssetStatusEnum.TO_BE_SCRAPPED
        ));

        // AVAILABLE_IDLE can go to self, rent, closed, fault, maintaining, or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.AVAILABLE_IDLE, EnumSet.of(
                AssetStatusEnum.AVAILABLE_SELF,
                AssetStatusEnum.AVAILABLE_RENT,
                AssetStatusEnum.TEMP_CLOSED,
                AssetStatusEnum.FAULT,
                AssetStatusEnum.MAINTAINING,
                AssetStatusEnum.TO_BE_SCRAPPED
        ));

        // TEMP_CLOSED can go back to available states, fault, maintaining, or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.TEMP_CLOSED, EnumSet.of(
                AssetStatusEnum.AVAILABLE_SELF,
                AssetStatusEnum.AVAILABLE_RENT,
                AssetStatusEnum.AVAILABLE_IDLE,
                AssetStatusEnum.FAULT,
                AssetStatusEnum.MAINTAINING,
                AssetStatusEnum.TO_BE_SCRAPPED
        ));

        // FAULT can go to maintaining or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.FAULT, EnumSet.of(
                AssetStatusEnum.MAINTAINING,
                AssetStatusEnum.TO_BE_SCRAPPED,
                AssetStatusEnum.SCRAPPED
        ));

        // MAINTAINING can go back to available states, fault, or to_be_scrapped
        TRANSITION_MAP.put(AssetStatusEnum.MAINTAINING, EnumSet.of(
                AssetStatusEnum.AVAILABLE_SELF,
                AssetStatusEnum.AVAILABLE_RENT,
                AssetStatusEnum.AVAILABLE_IDLE,
                AssetStatusEnum.FAULT,
                AssetStatusEnum.TO_BE_SCRAPPED
        ));

        // TO_BE_SCRAPPED can only go to scrapped
        TRANSITION_MAP.put(AssetStatusEnum.TO_BE_SCRAPPED, EnumSet.of(
                AssetStatusEnum.SCRAPPED
        ));

        // SCRAPPED is terminal - no transitions allowed
        TRANSITION_MAP.put(AssetStatusEnum.SCRAPPED, EnumSet.noneOf(AssetStatusEnum.class));
    }

    @Override
    @Transactional
    public Asset changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset == null) {
            throw new IllegalArgumentException("Asset not found: " + assetId);
        }

        AssetStatusEnum currentStatus = AssetStatusEnum.fromCode(asset.getStatus());
        if (currentStatus == null) {
            throw new IllegalStateException("Asset has invalid status: " + asset.getStatus());
        }

        // Validate transition
        if (!isValidTransition(currentStatus, newStatus)) {
            throw new InvalidStateTransitionException(currentStatus.getCode(), newStatus.getCode());
        }

        // Log the change
        log.info("Changing asset {} status from {} to {}, reason: {}",
                assetId, currentStatus.getCode(), newStatus.getCode(), reason);

        // Save status log
        saveStatusLog(assetId, currentStatus.getCode(), newStatus.getCode(), reason, null, operatorId);

        // Update asset
        asset.setStatus(newStatus.getCode());
        assetMapper.updateById(asset);

        return asset;
    }

    @Override
    @Transactional
    public Asset changeStatusByMaintenance(Long assetId, AssetStatusEnum newStatus, Long maintOrderId, Long operatorId) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset == null) {
            throw new IllegalArgumentException("Asset not found: " + assetId);
        }

        String currentStatus = asset.getStatus();
        String reason = "Status changed by maintenance order: " + maintOrderId;

        log.info("Changing asset {} status from {} to {} by maintenance order {}",
                assetId, currentStatus, newStatus.getCode(), maintOrderId);

        // Save status log with maintenance order reference
        saveStatusLog(assetId, currentStatus, newStatus.getCode(), reason, maintOrderId, operatorId);

        // Update asset
        asset.setStatus(newStatus.getCode());
        assetMapper.updateById(asset);

        return asset;
    }

    @Override
    @Transactional
    public Asset restoreStatusAfterMaintenance(Long assetId, Long maintOrderId, String originalStatus, Long operatorId) {
        Asset asset = assetMapper.selectById(assetId);
        if (asset == null) {
            throw new IllegalArgumentException("Asset not found: " + assetId);
        }

        String currentStatus = asset.getStatus();
        String reason = "Status restored after maintenance order completion: " + maintOrderId;

        log.info("Restoring asset {} status from {} to {} after maintenance order {} completion",
                assetId, currentStatus, originalStatus, maintOrderId);

        // Save status log with maintenance order reference
        saveStatusLog(assetId, currentStatus, originalStatus, reason, maintOrderId, operatorId);

        // Update asset
        asset.setStatus(originalStatus);
        assetMapper.updateById(asset);

        return asset;
    }

    @Override
    public boolean isValidTransition(AssetStatusEnum fromStatus, AssetStatusEnum toStatus) {
        if (fromStatus == null || toStatus == null) {
            return false;
        }
        if (fromStatus == toStatus) {
            return true; // Same status is always valid (no-op)
        }
        Set<AssetStatusEnum> validTargets = TRANSITION_MAP.get(fromStatus);
        return validTargets != null && validTargets.contains(toStatus);
    }

    @Override
    public Set<AssetStatusEnum> getValidNextStatuses(AssetStatusEnum currentStatus) {
        if (currentStatus == null) {
            return EnumSet.noneOf(AssetStatusEnum.class);
        }
        Set<AssetStatusEnum> validTargets = TRANSITION_MAP.get(currentStatus);
        return validTargets != null ? EnumSet.copyOf(validTargets) : EnumSet.noneOf(AssetStatusEnum.class);
    }

    @Override
    public List<AssetStatusLog> getStatusHistory(Long assetId) {
        return statusLogMapper.findByAssetId(assetId);
    }

    /**
     * Save status change log
     */
    private void saveStatusLog(Long assetId, String beforeStatus, String afterStatus,
                               String reason, Long maintOrderId, Long operatorId) {
        AssetStatusLog statusLog = new AssetStatusLog();
        statusLog.setAssetId(assetId);
        statusLog.setBeforeStatus(beforeStatus);
        statusLog.setAfterStatus(afterStatus);
        statusLog.setReason(reason);
        statusLog.setMaintOrderId(maintOrderId);
        statusLog.setOperatorId(operatorId);
        statusLog.setChangeTime(new Date());
        statusLogMapper.insert(statusLog);
    }
}
