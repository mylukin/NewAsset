package com.ruoyi.asset.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for updating venue asset
 */
public class AssetVenueUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Asset ID cannot be null")
    private Long id;

    // Base asset fields

    @NotBlank(message = "Asset name cannot be blank")
    @Size(max = 200, message = "Asset name must be at most 200 characters")
    private String assetName;

    @Size(max = 64, message = "Building must be at most 64 characters")
    private String building;

    @Size(max = 32, message = "Floor must be at most 32 characters")
    private String floor;

    @Size(max = 64, message = "Room number must be at most 64 characters")
    private String roomNo;

    @Size(max = 500, message = "Location description must be at most 500 characters")
    private String locationDesc;

    @Size(max = 32, message = "Ownership type must be at most 32 characters")
    private String ownershipType;

    @Size(max = 200, message = "Owner organization must be at most 200 characters")
    private String ownerOrg;

    private Long useDeptId;

    private Long dutyUserId;

    private Date purchaseDate;

    private Date startUseDate;

    private BigDecimal originalValue;

    @Size(max = 32, message = "Depreciation method must be at most 32 characters")
    private String depreciationMethod;

    @Size(max = 100, message = "Brand must be at most 100 characters")
    private String brand;

    @Size(max = 100, message = "Model must be at most 100 characters")
    private String model;

    @Size(max = 200, message = "Supplier must be at most 200 characters")
    private String supplier;

    private Date warrantyExpireDate;

    @Size(max = 500, message = "Remark must be at most 500 characters")
    private String remark;

    // Venue extension fields

    @NotBlank(message = "Venue name cannot be blank")
    @Size(max = 100, message = "Venue name must be at most 100 characters")
    private String venueName;

    @Size(max = 32, message = "Venue type must be at most 32 characters")
    private String venueType;

    private BigDecimal area;

    @Min(value = 0, message = "Capacity cannot be negative")
    private Integer capacity;

    @Size(max = 500, message = "Equipment list must be at most 500 characters")
    private String equipment;

    private BigDecimal hourlyRate;

    private BigDecimal dailyRate;

    private String bookable;

    @Size(max = 64, message = "Contact person must be at most 64 characters")
    private String contactPerson;

    @Size(max = 32, message = "Contact phone must be at most 32 characters")
    private String contactPhone;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getOwnerOrg() {
        return ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    public Long getUseDeptId() {
        return useDeptId;
    }

    public void setUseDeptId(Long useDeptId) {
        this.useDeptId = useDeptId;
    }

    public Long getDutyUserId() {
        return dutyUserId;
    }

    public void setDutyUserId(Long dutyUserId) {
        this.dutyUserId = dutyUserId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getStartUseDate() {
        return startUseDate;
    }

    public void setStartUseDate(Date startUseDate) {
        this.startUseDate = startUseDate;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(Date warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
