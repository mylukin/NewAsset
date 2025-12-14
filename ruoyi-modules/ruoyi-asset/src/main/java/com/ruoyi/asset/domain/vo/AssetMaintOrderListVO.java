package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Maintenance Order list view VO
 */
public class AssetMaintOrderListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    private Long id;

    /**
     * Order number
     */
    private String orderNo;

    /**
     * Asset ID
     */
    private Long assetId;

    /**
     * Asset code
     */
    private String assetCode;

    /**
     * Asset name
     */
    private String assetName;

    /**
     * Project ID
     */
    private Long projectId;

    /**
     * Project name
     */
    private String projectName;

    /**
     * Order type code
     */
    private String orderType;

    /**
     * Order type label
     */
    private String orderTypeLabel;

    /**
     * Fault type code
     */
    private String faultType;

    /**
     * Fault type label
     */
    private String faultTypeLabel;

    /**
     * Order title
     */
    private String title;

    /**
     * Priority code
     */
    private String priority;

    /**
     * Priority label
     */
    private String priorityLabel;

    /**
     * Required finish time
     */
    private Date requireFinishTime;

    /**
     * Status code
     */
    private String status;

    /**
     * Status label
     */
    private String statusLabel;

    /**
     * Current handler ID
     */
    private Long currentHandlerId;

    /**
     * Current handler name
     */
    private String currentHandlerName;

    /**
     * Requester ID
     */
    private Long requesterId;

    /**
     * Requester name
     */
    private String requesterName;

    /**
     * Create time
     */
    private Date createTime;

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

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeLabel() {
        return orderTypeLabel;
    }

    public void setOrderTypeLabel(String orderTypeLabel) {
        this.orderTypeLabel = orderTypeLabel;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getFaultTypeLabel() {
        return faultTypeLabel;
    }

    public void setFaultTypeLabel(String faultTypeLabel) {
        this.faultTypeLabel = faultTypeLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityLabel() {
        return priorityLabel;
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
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

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public Long getCurrentHandlerId() {
        return currentHandlerId;
    }

    public void setCurrentHandlerId(Long currentHandlerId) {
        this.currentHandlerId = currentHandlerId;
    }

    public String getCurrentHandlerName() {
        return currentHandlerName;
    }

    public void setCurrentHandlerName(String currentHandlerName) {
        this.currentHandlerName = currentHandlerName;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
