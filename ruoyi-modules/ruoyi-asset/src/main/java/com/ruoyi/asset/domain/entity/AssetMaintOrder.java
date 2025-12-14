package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Maintenance Order entity
 * Maps to t_asset_maint_order table
 */
@TableName("t_asset_maint_order")
public class AssetMaintOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Order number (unique)
     */
    @NotBlank(message = "Order number cannot be blank")
    @Size(max = 64, message = "Order number must be at most 64 characters")
    private String orderNo;

    /**
     * Asset ID (FK)
     */
    @NotNull(message = "Asset ID cannot be null")
    private Long assetId;

    /**
     * Project ID
     */
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    /**
     * Order type: fault/repair/inspection/maintenance
     */
    @NotBlank(message = "Order type cannot be blank")
    @Size(max = 32, message = "Order type must be at most 32 characters")
    private String orderType;

    /**
     * Fault type
     */
    @Size(max = 32, message = "Fault type must be at most 32 characters")
    private String faultType;

    /**
     * Order title
     */
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    /**
     * Description
     */
    private String description;

    /**
     * Priority: NORMAL/IMPORTANT/URGENT
     */
    @Size(max = 32, message = "Priority must be at most 32 characters")
    private String priority;

    /**
     * Required finish time
     */
    private Date requireFinishTime;

    /**
     * Status: WAIT_ASSIGN/WAIT_ACCEPT/PROCESSING/WAIT_CONFIRM/CLOSED/CANCELED
     */
    @NotBlank(message = "Status cannot be blank")
    @Size(max = 32, message = "Status must be at most 32 characters")
    private String status;

    /**
     * Original asset status when order created
     */
    @Size(max = 32, message = "Origin asset status must be at most 32 characters")
    private String originAssetStatus;

    /**
     * Current handler user ID
     */
    private Long currentHandlerId;

    /**
     * Requester user ID
     */
    private Long requesterId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getRequireFinishTime() {
        return requireFinishTime;
    }

    public void setRequireFinishTime(Date requireFinishTime) {
        this.requireFinishTime = requireFinishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginAssetStatus() {
        return originAssetStatus;
    }

    public void setOriginAssetStatus(String originAssetStatus) {
        this.originAssetStatus = originAssetStatus;
    }

    public Long getCurrentHandlerId() {
        return currentHandlerId;
    }

    public void setCurrentHandlerId(Long currentHandlerId) {
        this.currentHandlerId = currentHandlerId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public String toString() {
        return "AssetMaintOrder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", assetId=" + assetId +
                ", projectId=" + projectId +
                ", orderType='" + orderType + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
