package com.ruoyi.asset.domain.maintenance;

/**
 * Maintenance Work Order Status Enum
 *
 * @author ruoyi
 */
public enum MaintenanceWorkOrderStatus {

    NEW("new", "New"),
    IN_PROGRESS("in_progress", "In Progress"),
    PENDING_PARTS("pending_parts", "Pending Parts"),
    ON_HOLD("on_hold", "On Hold"),
    COMPLETED("completed", "Completed"),
    CANCELLED("cancelled", "Cancelled");

    private final String code;
    private final String label;

    MaintenanceWorkOrderStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static MaintenanceWorkOrderStatus fromCode(String code) {
        for (MaintenanceWorkOrderStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}
