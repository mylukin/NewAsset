package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Facility asset extension entity
 * Maps to t_asset_facility table
 * Links to t_asset via asset_id
 */
@TableName("t_asset_facility")
public class AssetFacility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK, also primary key)
     */
    @TableId(value = "asset_id")
    private Long assetId;

    /**
     * Facility type (elevator/hvac/fire_system/etc)
     */
    @Size(max = 32, message = "Facility type must be at most 32 characters")
    private String facilityType;

    /**
     * Installation position description
     */
    @Size(max = 200, message = "Installation position must be at most 200 characters")
    private String installPosition;

    /**
     * Equipment number
     */
    @Size(max = 64, message = "Equipment number must be at most 64 characters")
    private String equipmentNo;

    /**
     * Maintenance organization
     */
    @Size(max = 200, message = "Maintenance organization must be at most 200 characters")
    private String maintOrg;

    /**
     * Maintenance phone
     */
    @Size(max = 32, message = "Maintenance phone must be at most 32 characters")
    private String maintPhone;

    // Getters and Setters

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
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

    @Override
    public String toString() {
        return "AssetFacility{" +
                "assetId=" + assetId +
                ", facilityType='" + facilityType + '\'' +
                ", equipmentNo='" + equipmentNo + '\'' +
                ", installPosition='" + installPosition + '\'' +
                '}';
    }
}
