package com.ruoyi.asset.domain.enums;

/**
 * Maintenance operation type enumeration
 */
public enum MaintOperationType {

    CREATE("CREATE", "Create Order"),
    ASSIGN("ASSIGN", "Assign Handler"),
    ACCEPT("ACCEPT", "Accept Order"),
    START_HANDLE("START_HANDLE", "Start Handling"),
    COMPLETE("COMPLETE", "Complete Work"),
    CONFIRM("CONFIRM", "Confirm Completion"),
    REJECT("REJECT", "Reject"),
    CANCEL("CANCEL", "Cancel Order");

    private final String code;
    private final String description;

    MaintOperationType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MaintOperationType fromCode(String code) {
        for (MaintOperationType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
