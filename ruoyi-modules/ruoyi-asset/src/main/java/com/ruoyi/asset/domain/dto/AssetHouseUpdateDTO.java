package com.ruoyi.asset.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for updating house asset
 */
public class AssetHouseUpdateDTO implements Serializable {

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

    // House extension fields

    private BigDecimal buildingArea;

    private BigDecimal innerArea;

    @Size(max = 32, message = "House type must be at most 32 characters")
    private String houseType;

    @Size(max = 32, message = "House usage must be at most 32 characters")
    private String houseUsage;

    @Size(max = 32, message = "Current usage must be at most 32 characters")
    private String currentUsage;

    @Size(max = 100, message = "Current user must be at most 100 characters")
    private String currentUser;

    @Size(max = 64, message = "Contract number must be at most 64 characters")
    private String contractNo;

    private BigDecimal rentUnitPrice;

    private BigDecimal rentTotal;

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

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(BigDecimal innerArea) {
        this.innerArea = innerArea;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseUsage() {
        return houseUsage;
    }

    public void setHouseUsage(String houseUsage) {
        this.houseUsage = houseUsage;
    }

    public String getCurrentUsage() {
        return currentUsage;
    }

    public void setCurrentUsage(String currentUsage) {
        this.currentUsage = currentUsage;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getRentUnitPrice() {
        return rentUnitPrice;
    }

    public void setRentUnitPrice(BigDecimal rentUnitPrice) {
        this.rentUnitPrice = rentUnitPrice;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }

    public void setRentTotal(BigDecimal rentTotal) {
        this.rentTotal = rentTotal;
    }
}
