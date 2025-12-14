package com.ruoyi.asset.domain.parking;

/**
 * Parking Type Enum
 *
 * @author ruoyi
 */
public enum ParkingType {

    COVERED("covered", "Covered Parking"),
    OPEN("open", "Open Parking"),
    UNDERGROUND("underground", "Underground Parking"),
    MECHANICAL("mechanical", "Mechanical Parking"),
    VIP("vip", "VIP Parking");

    private final String code;
    private final String label;

    ParkingType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static ParkingType fromCode(String code) {
        for (ParkingType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown parking type code: " + code);
    }
}
