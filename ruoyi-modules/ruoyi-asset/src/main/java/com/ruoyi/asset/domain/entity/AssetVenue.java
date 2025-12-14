package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Venue asset extension entity
 * Maps to t_asset_venue table
 * Links to t_asset via asset_id
 */
@TableName("t_asset_venue")
public class AssetVenue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK, also primary key)
     */
    @TableId(value = "asset_id")
    private Long assetId;

    /**
     * Venue name/number
     */
    @NotBlank(message = "Venue name is required")
    @Size(max = 100, message = "Venue name must be at most 100 characters")
    private String venueName;

    /**
     * Venue type (meeting_room, conference_hall, training_room, multipurpose, etc.)
     */
    @Size(max = 32, message = "Venue type must be at most 32 characters")
    private String venueType;

    /**
     * Area in square meters
     */
    private BigDecimal area;

    /**
     * Seating capacity
     */
    @Min(value = 0, message = "Capacity cannot be negative")
    private Integer capacity;

    /**
     * Equipment list (projector, whiteboard, video conferencing, etc.)
     */
    @Size(max = 500, message = "Equipment list must be at most 500 characters")
    private String equipment;

    /**
     * Hourly rate for booking
     */
    private BigDecimal hourlyRate;

    /**
     * Daily rate for booking
     */
    private BigDecimal dailyRate;

    /**
     * Whether the venue is bookable (0=no, 1=yes)
     */
    private String bookable;

    /**
     * Contact person for venue management
     */
    @Size(max = 64, message = "Contact person must be at most 64 characters")
    private String contactPerson;

    /**
     * Contact phone
     */
    @Size(max = 32, message = "Contact phone must be at most 32 characters")
    private String contactPhone;

    // Getters and Setters

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getBookable() {
        return bookable;
    }

    public void setBookable(String bookable) {
        this.bookable = bookable;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "AssetVenue{" +
                "assetId=" + assetId +
                ", venueName='" + venueName + '\'' +
                ", venueType='" + venueType + '\'' +
                ", area=" + area +
                ", capacity=" + capacity +
                '}';
    }
}
