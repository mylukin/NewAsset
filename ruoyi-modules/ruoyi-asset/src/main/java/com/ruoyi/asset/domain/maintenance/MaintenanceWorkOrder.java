package com.ruoyi.asset.domain.maintenance;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Maintenance Work Order Entity
 * Represents a maintenance/repair work order for assets
 *
 * @author ruoyi
 */
public class MaintenanceWorkOrder {

    public static final String MODULE = "maintenance";

    /** Primary key */
    private Long id;

    /** Work order number */
    private String orderNumber;

    /** Related asset ID */
    private Long assetId;

    /** Work order title */
    private String title;

    /** Description of the issue or work to be done */
    private String description;

    /** Work order status */
    private MaintenanceWorkOrderStatus status = MaintenanceWorkOrderStatus.NEW;

    /** Priority (1=Low, 2=Medium, 3=High, 4=Critical) */
    private Integer priority;

    /** Assigned technician */
    private String assignedTo;

    /** Requester */
    private String requestedBy;

    /** Request date/time */
    private LocalDateTime requestedAt;

    /** Scheduled start date/time */
    private LocalDateTime scheduledStart;

    /** Scheduled end date/time */
    private LocalDateTime scheduledEnd;

    /** Actual start date/time */
    private LocalDateTime actualStart;

    /** Actual end date/time */
    private LocalDateTime actualEnd;

    /** Estimated cost */
    private BigDecimal estimatedCost;

    /** Actual cost */
    private BigDecimal actualCost;

    /** Resolution notes */
    private String resolutionNotes;

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public MaintenanceWorkOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    public MaintenanceWorkOrderStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceWorkOrderStatus status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getScheduledStart() {
        return scheduledStart;
    }

    public void setScheduledStart(LocalDateTime scheduledStart) {
        this.scheduledStart = scheduledStart;
    }

    public LocalDateTime getScheduledEnd() {
        return scheduledEnd;
    }

    public void setScheduledEnd(LocalDateTime scheduledEnd) {
        this.scheduledEnd = scheduledEnd;
    }

    public LocalDateTime getActualStart() {
        return actualStart;
    }

    public void setActualStart(LocalDateTime actualStart) {
        this.actualStart = actualStart;
    }

    public LocalDateTime getActualEnd() {
        return actualEnd;
    }

    public void setActualEnd(LocalDateTime actualEnd) {
        this.actualEnd = actualEnd;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public BigDecimal getActualCost() {
        return actualCost;
    }

    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    public String getResolutionNotes() {
        return resolutionNotes;
    }

    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }

    public void setExtensionField(String key, Object value) {
        this.extensionFields.put(key, value);
    }

    public Object getExtensionField(String key) {
        return this.extensionFields.get(key);
    }

    public Map<String, Object> getExtensionFields() {
        return extensionFields;
    }

    public void setExtensionFields(Map<String, Object> extensionFields) {
        this.extensionFields = extensionFields;
    }
}
