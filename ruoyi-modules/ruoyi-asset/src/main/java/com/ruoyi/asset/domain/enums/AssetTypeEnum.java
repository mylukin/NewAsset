package com.ruoyi.asset.domain.enums;

/**
 * Asset type enumeration
 */
public enum AssetTypeEnum {

    HOUSE("HOUSE", "House Asset"),
    PARKING("PARKING", "Parking Asset"),
    FACILITY("FACILITY", "Facility Asset"),
    VENUE("VENUE", "Venue Asset"),
    OFFICE("OFFICE", "Office Asset");

    private final String code;
    private final String description;

    AssetTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AssetTypeEnum fromCode(String code) {
        for (AssetTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
