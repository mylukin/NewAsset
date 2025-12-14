package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Maintenance Order detail view VO
 */
public class AssetMaintOrderDetailVO implements Serializable {

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
     * Asset type code
     */
    private String assetType;

    /**
     * Asset type label
     */
    private String assetTypeLabel;

    /**
     * Asset location
     */
    private String assetLocation;

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
     * Description
     */
    private String description;

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
     * Original asset status when order created
     */
    private String originAssetStatus;

    /**
     * Original asset status label
     */
    private String originAssetStatusLabel;

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
     * Create by
     */
    private String createBy;

    /**
     * Create time
     */
    private Date createTime;

    /**
     * Update by
     */
    private String updateBy;

    /**
     * Update time
     */
    private Date updateTime;

    /**
     * Maintenance logs/history
     */
    private List<MaintLogVO> maintLogs;

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

    public String getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
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

    public String getOriginAssetStatus() {
        return originAssetStatus;
    }

    public void setOriginAssetStatus(String originAssetStatus) {
        this.originAssetStatus = originAssetStatus;
    }

    public String getOriginAssetStatusLabel() {
        return originAssetStatusLabel;
    }

    public void setOriginAssetStatusLabel(String originAssetStatusLabel) {
        this.originAssetStatusLabel = originAssetStatusLabel;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<MaintLogVO> getMaintLogs() {
        return maintLogs;
    }

    public void setMaintLogs(List<MaintLogVO> maintLogs) {
        this.maintLogs = maintLogs;
    }

    /**
     * Maintenance log inner VO
     */
    public static class MaintLogVO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        private String fromStatus;
        private String fromStatusLabel;
        private String toStatus;
        private String toStatusLabel;
        private Long operatorId;
        private String operatorName;
        private String operationType;
        private String operationTypeLabel;
        private String content;
        private Date opTime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFromStatus() {
            return fromStatus;
        }

        public void setFromStatus(String fromStatus) {
            this.fromStatus = fromStatus;
        }

        public String getFromStatusLabel() {
            return fromStatusLabel;
        }

        public void setFromStatusLabel(String fromStatusLabel) {
            this.fromStatusLabel = fromStatusLabel;
        }

        public String getToStatus() {
            return toStatus;
        }

        public void setToStatus(String toStatus) {
            this.toStatus = toStatus;
        }

        public String getToStatusLabel() {
            return toStatusLabel;
        }

        public void setToStatusLabel(String toStatusLabel) {
            this.toStatusLabel = toStatusLabel;
        }

        public Long getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(Long operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }

        public String getOperationTypeLabel() {
            return operationTypeLabel;
        }

        public void setOperationTypeLabel(String operationTypeLabel) {
            this.operationTypeLabel = operationTypeLabel;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getOpTime() {
            return opTime;
        }

        public void setOpTime(Date opTime) {
            this.opTime = opTime;
        }
    }
}
