package com.ruoyi.asset.domain.enums;

/**
 * Unified asset status enumeration
 */
public enum AssetStatusEnum {

    UNDER_CONSTRUCTION("UNDER_CONSTRUCTION", "Under Construction"),
    AVAILABLE_SELF("AVAILABLE_SELF", "Available - Self Use"),
    AVAILABLE_RENT("AVAILABLE_RENT", "Available - For Rent"),
    AVAILABLE_IDLE("AVAILABLE_IDLE", "Available - Idle"),
    IDLE("IDLE", "Idle"),
    IN_USE("IN_USE", "In Use"),
    TEMP_CLOSED("TEMP_CLOSED", "Temporarily Closed"),
    FAULT("FAULT", "Fault/Out of Service"),
    MAINTAINING("MAINTAINING", "Under Maintenance"),
    TO_BE_SCRAPPED("TO_BE_SCRAPPED", "To Be Scrapped"),
    SCRAPPED("SCRAPPED", "Scrapped");

    private final String code;
    private final String description;

    AssetStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AssetStatusEnum fromCode(String code) {
        for (AssetStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
