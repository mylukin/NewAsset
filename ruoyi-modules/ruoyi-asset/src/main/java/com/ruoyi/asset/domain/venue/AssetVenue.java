package com.ruoyi.asset.domain.venue;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Venue Asset Entity
 * Represents a venue/meeting room with capacity and availability info
 *
 * @author ruoyi
 */
public class AssetVenue {

    public static final String MODULE = "venue";

    /** Primary key */
    private Long id;

    /** Asset ID */
    private String assetId;

    /** Asset name */
    private String assetName;

    /** Venue type (meeting_room, basketball, conference_hall, etc.) */
    private String venueType;

    /** Maximum capacity */
    private Integer capacity;

    /** Current venue status */
    private VenueStatus currentVenueStatus = VenueStatus.AVAILABLE;

    /** Building */
    private String building;

    /** Floor */
    private String floor;

    /** Room number */
    private String roomNumber;

    /** Area in square meters */
    private BigDecimal area;

    /** Hourly rate */
    private BigDecimal hourlyRate;

    /** Opening time */
    private LocalTime openingTime;

    /** Closing time */
    private LocalTime closingTime;

    /** Has projector */
    private Boolean hasProjector;

    /** Has video conferencing */
    private Boolean hasVideoConferencing;

    /** Has whiteboard */
    private Boolean hasWhiteboard;

    /** Extension fields for flexible data storage */
    private Map<String, Object> extensionFields = new HashMap<>();

    public AssetVenue() {
    }

    public boolean isAvailable() {
        return VenueStatus.AVAILABLE.equals(this.currentVenueStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public VenueStatus getCurrentVenueStatus() {
        return currentVenueStatus;
    }

    public void setCurrentVenueStatus(VenueStatus currentVenueStatus) {
        this.currentVenueStatus = currentVenueStatus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public Boolean getHasVideoConferencing() {
        return hasVideoConferencing;
    }

    public void setHasVideoConferencing(Boolean hasVideoConferencing) {
        this.hasVideoConferencing = hasVideoConferencing;
    }

    public Boolean getHasWhiteboard() {
        return hasWhiteboard;
    }

    public void setHasWhiteboard(Boolean hasWhiteboard) {
        this.hasWhiteboard = hasWhiteboard;
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
