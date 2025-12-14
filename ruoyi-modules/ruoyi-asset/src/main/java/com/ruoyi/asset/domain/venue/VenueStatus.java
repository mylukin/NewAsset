package com.ruoyi.asset.domain.venue;

/**
 * Venue Status Enum
 *
 * @author ruoyi
 */
public enum VenueStatus {

    AVAILABLE("available", "Available"),
    OCCUPIED("occupied", "Occupied"),
    RESERVED("reserved", "Reserved"),
    MAINTENANCE("maintenance", "Under Maintenance"),
    CLOSED("closed", "Closed");

    private final String code;
    private final String label;

    VenueStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static VenueStatus fromCode(String code) {
        for (VenueStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown venue status code: " + code);
    }
}
