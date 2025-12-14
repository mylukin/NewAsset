package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Facility asset list view object
 * Used for displaying facility assets in list views
 */
public class AssetFacilityListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Base asset fields
    private Long id;
    private String assetCode;
    private String assetName;
    private String status;
    private String statusLabel;
    private Long projectId;
    private String projectName;
    private String building;
    private String floor;
    private String roomNo;
    private String locationDesc;
    private BigDecimal originalValue;
    private BigDecimal netValue;

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
