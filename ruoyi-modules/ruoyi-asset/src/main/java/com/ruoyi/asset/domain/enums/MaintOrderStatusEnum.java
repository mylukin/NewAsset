package com.ruoyi.asset.domain.enums;

/**
 * Maintenance order status enumeration
 */
public enum MaintOrderStatusEnum {

    WAIT_ASSIGN("WAIT_ASSIGN", "Waiting for Assignment"),
    WAIT_ACCEPT("WAIT_ACCEPT", "Waiting for Acceptance"),
    PROCESSING("PROCESSING", "Processing"),
    WAIT_CONFIRM("WAIT_CONFIRM", "Waiting for Confirmation"),
    CLOSED("CLOSED", "Closed"),
    CANCELED("CANCELED", "Canceled");

    private final String code;
    private final String description;

    MaintOrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MaintOrderStatusEnum fromCode(String code) {
        for (MaintOrderStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
