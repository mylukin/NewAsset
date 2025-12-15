package com.ruoyi.asset.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.asset.domain.entity.Asset;
import com.ruoyi.asset.domain.entity.AssetStatusChangeLog;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.asset.mapper.AssetMapper;
import com.ruoyi.asset.mapper.AssetStatusChangeLogMapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Asset Status Service
 *
 * Handles asset status changes with validation and audit logging
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetStatusService {

    private final AssetMapper assetMapper;
    private final AssetStatusChangeLogMapper changeLogMapper;

    /**
     * Change asset status with validation and logging
     *
     * @param assetId asset ID
     * @param newStatus new status
     * @param reason reason for change
     * @param operatorId operator user ID
     * @return updated asset
     */
    @Log(title = "Asset Status Change", businessType = "EDIT")
    @Transactional(rollbackFor = Exception.class)
    public Asset changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId) {
        // Get current asset
        Asset asset = assetMapper.selectById(assetId);
        if (asset == null) {
            throw new ServiceException("Asset not found: " + assetId);
        }

        AssetStatusEnum currentStatus = AssetStatusEnum.valueOf(asset.getStatus().toUpperCase());

        // Validate transition
        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new ServiceException(
                String.format("Invalid status transition: %s cannot change to %s",
                    currentStatus.getCode(), newStatus.getCode()));
        }

        // Update asset status
        Asset updateAsset = new Asset();
        updateAsset.setId(assetId);
        updateAsset.setStatus(newStatus.getCode());
        updateAsset.setStatusChangedBy(String.valueOf(operatorId));
        updateAsset.setStatusChangedAt(java.time.LocalDateTime.now());
        updateAsset.setUpdateBy(String.valueOf(operatorId));
        updateAsset.setUpdateTime(java.time.LocalDateTime.now());

        int rows = assetMapper.updateById(updateAsset);
        if (rows == 0) {
            throw new ServiceException("Failed to update asset status");
        }

        // Log status change
        logStatusChange(assetId, currentStatus, newStatus, reason, operatorId);

        // Return updated asset
        return assetMapper.selectById(assetId);
    }

    /**
     * Log status change to database
     */
    private void logStatusChange(Long assetId, AssetStatusEnum fromStatus,
                                 AssetStatusEnum toStatus, String reason, Long operatorId) {
        try {
            AssetStatusChangeLog changeLog = new AssetStatusChangeLog();
            changeLog.setAssetId(assetId);
            changeLog.setFromStatus(fromStatus.getCode());
            changeLog.setToStatus(toStatus.getCode());
            changeLog.setOperatorId(operatorId);
            changeLog.setReason(reason);
            changeLog.setChangeTime(java.time.LocalDateTime.now());

            changeLogMapper.insert(changeLog);
        } catch (Exception e) {
            // Log failure shouldn't block the status change
            log.error("Failed to log status change for asset {}", assetId, e);
        }
    }
}
