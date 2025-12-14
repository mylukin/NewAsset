package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Parking asset list view object
 * Used for displaying parking assets in list views
 */
public class AssetParkingListVO implements Serializable {

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
    private String locationDesc;
    private BigDecimal originalValue;
    private BigDecimal netValue;

    // Parking extension fields
    private String parkingNo;
    private String parkingZone;
    private String parkingZoneLabel;
    private String parkingType;
    private String parkingTypeLabel;
    private BigDecimal area;
    private String currentUser;
    private String plateNo;
    private BigDecimal rentPrice;

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

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public String getParkingZone() {
        return parkingZone;
    }

    public void setParkingZone(String parkingZone) {
        this.parkingZone = parkingZone;
    }

    public String getParkingZoneLabel() {
        return parkingZoneLabel;
    }

    public void setParkingZoneLabel(String parkingZoneLabel) {
        this.parkingZoneLabel = parkingZoneLabel;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getParkingTypeLabel() {
        return parkingTypeLabel;
    }

    public void setParkingTypeLabel(String parkingTypeLabel) {
        this.parkingTypeLabel = parkingTypeLabel;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }
}
