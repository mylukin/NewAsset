package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.asset.domain.enums.AssetStatusEnum;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Asset entity - Base asset table
 * Maps to t_asset table
 */
@TableName("t_asset")
public class Asset extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset code (unique)
     */
    @NotBlank(message = "Asset code cannot be blank")
    @Size(max = 64, message = "Asset code must be at most 64 characters")
    private String assetCode;

    /**
     * Asset name
     */
    @NotBlank(message = "Asset name cannot be blank")
    @Size(max = 200, message = "Asset name must be at most 200 characters")
    private String assetName;

    /**
     * Asset type: HOUSE/PARKING/FACILITY/VENUE/OFFICE
     */
    @NotBlank(message = "Asset type cannot be blank")
    @Size(max = 32, message = "Asset type must be at most 32 characters")
    private String assetType;

    /**
     * Project ID
     */
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    /**
     * Building/zone code
     */
    @Size(max = 64, message = "Building must be at most 64 characters")
    private String building;

    /**
     * Floor
     */
    @Size(max = 32, message = "Floor must be at most 32 characters")
    private String floor;

    /**
     * Room/space number
     */
    @Size(max = 64, message = "Room number must be at most 64 characters")
    private String roomNo;

    /**
     * Location description
     */
    @Size(max = 500, message = "Location description must be at most 500 characters")
    private String locationDesc;

    /**
     * Ownership type
     */
    @Size(max = 32, message = "Ownership type must be at most 32 characters")
    private String ownershipType;

    /**
     * Owner organization
     */
    @Size(max = 200, message = "Owner organization must be at most 200 characters")
    private String ownerOrg;

    /**
     * Using department ID
     */
    private Long useDeptId;

    /**
     * Responsible user ID
     */
    private Long dutyUserId;

    /**
     * Asset status
     */
    @TableField("status")
    private String status;

    /**
     * Purchase date
     */
    private Date purchaseDate;

    /**
     * Start use date
     */
    private Date startUseDate;

    /**
     * Original value
     */
    private BigDecimal originalValue;

    /**
     * Depreciation method
     */
    @Size(max = 32, message = "Depreciation method must be at most 32 characters")
    private String depreciationMethod;

    /**
     * Accumulated depreciation
     */
    private BigDecimal depreciationAmount;

    /**
     * Net value
     */
    private BigDecimal netValue;

    /**
     * Brand
     */
    @Size(max = 100, message = "Brand must be at most 100 characters")
    private String brand;

    /**
     * Model
     */
    @Size(max = 100, message = "Model must be at most 100 characters")
    private String model;

    /**
     * Supplier
     */
    @Size(max = 200, message = "Supplier must be at most 200 characters")
    private String supplier;

    /**
     * Warranty expiration date
     */
    private Date warrantyExpireDate;

    /**
     * Delete flag: 0=normal, 2=deleted
     */
    private String delFlag;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get status as enum
     */
    public AssetStatusEnum getStatusEnum() {
        return AssetStatusEnum.fromCode(this.status);
    }

    /**
     * Set status from enum
     */
    public void setStatusEnum(AssetStatusEnum statusEnum) {
        this.status = statusEnum != null ? statusEnum.getCode() : null;
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

    public BigDecimal getDepreciationAmount() {
        return depreciationAmount;
    }

    public void setDepreciationAmount(BigDecimal depreciationAmount) {
        this.depreciationAmount = depreciationAmount;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", assetCode='" + assetCode + '\'' +
                ", assetName='" + assetName + '\'' +
                ", assetType='" + assetType + '\'' +
                ", projectId=" + projectId +
                ", status='" + status + '\'' +
                '}';
    }
}
