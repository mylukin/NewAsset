package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Facility asset detail view object
 * Used for displaying facility asset detail information
 */
public class AssetFacilityDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Base asset fields
    private Long id;
    private String assetCode;
    private String assetName;
    private String assetType;
    private String status;
    private String statusLabel;
    private Long projectId;
    private String projectName;
    private String building;
    private String floor;
    private String roomNo;
    private String locationDesc;
    private String ownershipType;
    private String ownershipTypeLabel;
    private String ownerOrg;
    private Long useDeptId;
    private String useDeptName;
    private Long dutyUserId;
    private String dutyUserName;
    private Date purchaseDate;
    private Date startUseDate;
    private BigDecimal originalValue;
    private BigDecimal netValue;
    private String depreciationMethod;
    private String depreciationMethodLabel;
    private String brand;
    private String model;
    private String supplier;
    private Date warrantyExpireDate;
    private String remark;
    private Date createTime;
    private Date updateTime;

    // Facility extension fields
    private String facilityType;
    private String facilityTypeLabel;
    private String installPosition;
    private String equipmentNo;
    private String maintOrg;
    private String maintPhone;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getOwnershipTypeLabel() {
        return ownershipTypeLabel;
    }

    public void setOwnershipTypeLabel(String ownershipTypeLabel) {
        this.ownershipTypeLabel = ownershipTypeLabel;
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

    public String getUseDeptName() {
        return useDeptName;
    }

    public void setUseDeptName(String useDeptName) {
        this.useDeptName = useDeptName;
    }

    public Long getDutyUserId() {
        return dutyUserId;
    }

    public void setDutyUserId(Long dutyUserId) {
        this.dutyUserId = dutyUserId;
    }

    public String getDutyUserName() {
        return dutyUserName;
    }

    public void setDutyUserName(String dutyUserName) {
        this.dutyUserName = dutyUserName;
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

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }

    public String getDepreciationMethodLabel() {
        return depreciationMethodLabel;
    }

    public void setDepreciationMethodLabel(String depreciationMethodLabel) {
        this.depreciationMethodLabel = depreciationMethodLabel;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getFacilityTypeLabel() {
        return facilityTypeLabel;
    }

    public void setFacilityTypeLabel(String facilityTypeLabel) {
        this.facilityTypeLabel = facilityTypeLabel;
    }

    public String getInstallPosition() {
        return installPosition;
    }

    public void setInstallPosition(String installPosition) {
        this.installPosition = installPosition;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getMaintOrg() {
        return maintOrg;
    }

    public void setMaintOrg(String maintOrg) {
        this.maintOrg = maintOrg;
    }

    public String getMaintPhone() {
        return maintPhone;
    }

    public void setMaintPhone(String maintPhone) {
        this.maintPhone = maintPhone;
    }
}
