package com.ruoyi.asset.service.rule;

import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetStatusLog;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;

import java.util.List;
import java.util.Set;

/**
 * Asset status state machine service interface
 * Handles status transitions and validation
 */
public interface AssetStatusService {

    /**
     * Change asset status with validation
     *
     * @param assetId    Asset ID
     * @param newStatus  New status
     * @param reason     Reason for change
     * @param operatorId Operator user ID
     * @return Updated asset
     */
    Asset changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId);

    /**
     * Change asset status triggered by maintenance workflow
     *
     * @param assetId      Asset ID
     * @param newStatus    New status (typically MAINTAINING or FAULT)
     * @param maintOrderId Related maintenance order ID
     * @param operatorId   Operator user ID
     * @return Updated asset
     */
    Asset changeStatusByMaintenance(Long assetId, AssetStatusEnum newStatus, Long maintOrderId, Long operatorId);

    /**
     * Restore asset status after maintenance completion
     *
     * @param assetId        Asset ID
     * @param maintOrderId   Maintenance order ID
     * @param originalStatus Original status before maintenance
     * @param operatorId     Operator user ID
     * @return Updated asset
     */
    Asset restoreStatusAfterMaintenance(Long assetId, Long maintOrderId, String originalStatus, Long operatorId);

    /**
     * Check if a status transition is valid
     *
     * @param fromStatus Current status
     * @param toStatus   Target status
     * @return true if transition is valid
     */
    boolean isValidTransition(AssetStatusEnum fromStatus, AssetStatusEnum toStatus);

    /**
     * Get valid next statuses for a given status
     *
     * @param currentStatus Current status
     * @return Set of valid next statuses
     */
    Set<AssetStatusEnum> getValidNextStatuses(AssetStatusEnum currentStatus);

    /**
     * Get status change history for an asset
     *
     * @param assetId Asset ID
     * @return List of status logs
     */
    List<AssetStatusLog> getStatusHistory(Long assetId);
}
