package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Facility warranty expiring view object
 * Used for displaying facilities with warranty nearing expiration
 */
public class FacilityWarrantyExpiringVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String assetCode;
    private String assetName;
    private Long projectId;
    private String projectName;
    private String building;
    private String floor;
    private String facilityType;
    private String facilityTypeLabel;
    private String equipmentNo;
    private String maintOrg;
    private String maintPhone;
    private Date warrantyExpireDate;
    private Integer daysRemaining;

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

    public Date getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(Date warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
}
