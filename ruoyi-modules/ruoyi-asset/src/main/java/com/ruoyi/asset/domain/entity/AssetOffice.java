package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Office asset extension entity
 * Maps to t_asset_office table
 * Links to t_asset via asset_id
 */
@TableName("t_asset_office")
public class AssetOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK, also primary key)
     */
    @TableId(value = "asset_id")
    private Long assetId;

    /**
     * Office type (furniture, IT, other)
     */
    @Size(max = 32, message = "Office type must be at most 32 characters")
    private String officeType;

    /**
     * Workplace/desk number
     */
    @Size(max = 64, message = "Workplace number must be at most 64 characters")
    private String workplaceNo;

    /**
     * User name assigned to this asset
     */
    @Size(max = 100, message = "User name must be at most 100 characters")
    private String useUser;

    /**
     * Serial number (for IT equipment)
     */
    @Size(max = 100, message = "Serial number must be at most 100 characters")
    private String serialNo;

    /**
     * OS information (for computers)
     */
    @Size(max = 200, message = "OS info must be at most 200 characters")
    private String osInfo;

    /**
     * Configuration description
     */
    @Size(max = 500, message = "Configuration description must be at most 500 characters")
    private String configDesc;

    // Getters and Setters

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getWorkplaceNo() {
        return workplaceNo;
    }

    public void setWorkplaceNo(String workplaceNo) {
        this.workplaceNo = workplaceNo;
    }

    public String getUseUser() {
        return useUser;
    }

    public void setUseUser(String useUser) {
        this.useUser = useUser;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    @Override
    public String toString() {
        return "AssetOffice{" +
                "assetId=" + assetId +
                ", officeType='" + officeType + '\'' +
                ", workplaceNo='" + workplaceNo + '\'' +
                ", useUser='" + useUser + '\'' +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
