package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Maintenance Plan VO
 */
public class AssetMaintPlanVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String planName;
    private Long projectId;
    private String projectName;
    private String assetType;
    private String assetTypeLabel;
    private String assetCategory;
    private String cycleType;
    private String cycleTypeLabel;
    private Date nextGenerateTime;
    private String status;
    private String statusLabel;
    private String remark;
    private String createBy;
    private Date createTime;
    private Integer assetCount;
    private List<Long> assetIds;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetTypeLabel() {
        return assetTypeLabel;
    }

    public void setAssetTypeLabel(String assetTypeLabel) {
        this.assetTypeLabel = assetTypeLabel;
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

    public String getCycleTypeLabel() {
        return cycleTypeLabel;
    }

    public void setCycleTypeLabel(String cycleTypeLabel) {
        this.cycleTypeLabel = cycleTypeLabel;
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

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(Integer assetCount) {
        this.assetCount = assetCount;
    }

    public List<Long> getAssetIds() {
        return assetIds;
    }

    public void setAssetIds(List<Long> assetIds) {
        this.assetIds = assetIds;
    }
}
