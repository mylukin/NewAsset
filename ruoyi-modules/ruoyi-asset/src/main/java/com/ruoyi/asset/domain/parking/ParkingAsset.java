package com.ruoyi.asset.domain.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Parking Asset Entity
 * Represents a parking space asset with zone, type, and rental info
 *
 * @author ruoyi
 */
public class ParkingAsset {

    public static final String MODULE = "parking";

    /** Primary key */
    private Long id;

    /** Parking space number */
    private String spaceNumber;

    /** Zone code */
    private String zoneCode;

    /** Parking type */
    private ParkingType type;

    /** Floor level (for multi-story parking) */
    private String floor;

    /** Rental information */
    private RentalInfo rentalInfo;

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public ParkingAsset() {
    }

    /**
     * Factory method to create a ParkingAsset with validation
     */
    public static ParkingAsset create(String zoneCode, ParkingType type, RentalInfo rentalInfo) {
        if (zoneCode == null || zoneCode.isBlank()) {
            throw new IllegalArgumentException("Zone code cannot be blank");
        }
        Objects.requireNonNull(type, "Parking type cannot be null");

        ParkingAsset asset = new ParkingAsset();
        asset.setZoneCode(zoneCode);
        asset.setType(type);
        asset.setRentalInfo(rentalInfo);
        return asset;
    }

    public boolean isRented() {
        return rentalInfo != null && rentalInfo.isWithinRentalPeriod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(String spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public ParkingType getType() {
        return type;
    }

    public void setType(ParkingType type) {
        this.type = type;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public RentalInfo getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(RentalInfo rentalInfo) {
        this.rentalInfo = rentalInfo;
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
