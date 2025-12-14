package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Maintenance Plan entity
 * Maps to t_asset_maint_plan table
 */
@TableName("t_asset_maint_plan")
public class AssetMaintPlan extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Plan name
     */
    @NotBlank(message = "Plan name cannot be blank")
    @Size(max = 200, message = "Plan name must be at most 200 characters")
    private String planName;

    /**
     * Project ID (optional filter)
     */
    private Long projectId;

    /**
     * Asset type filter
     */
    @Size(max = 32, message = "Asset type must be at most 32 characters")
    private String assetType;

    /**
     * Asset category filter
     */
    @Size(max = 64, message = "Asset category must be at most 64 characters")
    private String assetCategory;

    /**
     * Cycle type: MONTHLY/QUARTERLY/YEARLY
     */
    @NotBlank(message = "Cycle type cannot be blank")
    @Size(max = 32, message = "Cycle type must be at most 32 characters")
    private String cycleType;

    /**
     * Next order generation time
     */
    private Date nextGenerateTime;

    /**
     * Status: ENABLED/DISABLED
     */
    @Size(max = 32, message = "Status must be at most 32 characters")
    private String status;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public Date getNextGenerateTime() {
        return nextGenerateTime;
    }

    public void setNextGenerateTime(Date nextGenerateTime) {
        this.nextGenerateTime = nextGenerateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AssetMaintPlan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", cycleType='" + cycleType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
