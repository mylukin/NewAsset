package com.ruoyi.asset.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO for creating maintenance order
 */
public class AssetMaintOrderCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * Initial handler ID (optional, for direct assignment)
     */
    private Long handlerId;

    // Getters and Setters

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

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }
}
