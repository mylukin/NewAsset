package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Maintenance Order Log VO for timeline display
 */
public class AssetMaintLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    private Long id;

    /**
     * Order ID
     */
    private Long orderId;

    /**
     * From status code
     */
    private String fromStatus;

    /**
     * From status label
     */
    private String fromStatusLabel;

    /**
     * To status code
     */
    private String toStatus;

    /**
     * To status label
     */
    private String toStatusLabel;

    /**
     * Operator user ID
     */
    private Long operatorId;

    /**
     * Operator name (for display)
     */
    private String operatorName;

    /**
     * Operation type code
     */
    private String operationType;

    /**
     * Operation type label
     */
    private String operationTypeLabel;

    /**
     * Operation content/remark
     */
    private String content;

    /**
     * Operation time
     */
    private Date opTime;

    /**
     * Timeline icon (based on operation type)
     */
    private String timelineIcon;

    /**
     * Timeline color (based on operation type)
     */
    private String timelineColor;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getTimelineIcon() {
        return timelineIcon;
    }

    public void setTimelineIcon(String timelineIcon) {
        this.timelineIcon = timelineIcon;
    }

    public String getTimelineColor() {
        return timelineColor;
    }

    public void setTimelineColor(String timelineColor) {
        this.timelineColor = timelineColor;
    }
}
