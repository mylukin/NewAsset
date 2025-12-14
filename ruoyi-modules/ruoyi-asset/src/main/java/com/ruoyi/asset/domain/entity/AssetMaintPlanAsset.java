package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Maintenance Plan Asset relation entity
 * Maps to t_asset_maint_plan_asset table
 */
@TableName("t_asset_maint_plan_asset")
public class AssetMaintPlanAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Plan ID
     */
    @NotNull(message = "Plan ID cannot be null")
    private Long planId;

    /**
     * Asset ID
     */
    @NotNull(message = "Asset ID cannot be null")
    private Long assetId;

    public AssetMaintPlanAsset() {
    }

    public AssetMaintPlanAsset(Long planId, Long assetId) {
        this.planId = planId;
        this.assetId = assetId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return "AssetMaintPlanAsset{" +
                "planId=" + planId +
                ", assetId=" + assetId +
                '}';
    }
}
