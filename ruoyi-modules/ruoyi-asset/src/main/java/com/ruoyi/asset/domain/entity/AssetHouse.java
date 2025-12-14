package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * House asset extension entity
 * Maps to t_asset_house table
 * Links to t_asset via asset_id
 */
@TableName("t_asset_house")
public class AssetHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK, also primary key)
     */
    @TableId(value = "asset_id")
    private Long assetId;

    /**
     * Building area (sqm)
     */
    private BigDecimal buildingArea;

    /**
     * Inner area (sqm)
     */
    private BigDecimal innerArea;

    /**
     * House type (1BR/2BR/etc)
     */
    @Size(max = 32, message = "House type must be at most 32 characters")
    private String houseType;

    /**
     * House usage (residential/commercial)
     */
    @Size(max = 32, message = "House usage must be at most 32 characters")
    private String houseUsage;

    /**
     * Current usage (self/rent/idle)
     */
    @Size(max = 32, message = "Current usage must be at most 32 characters")
    private String currentUsage;

    /**
     * Current user/tenant
     */
    @Size(max = 100, message = "Current user must be at most 100 characters")
    private String currentUser;

    /**
     * Contract number
     */
    @Size(max = 64, message = "Contract number must be at most 64 characters")
    private String contractNo;

    /**
     * Rent per sqm
     */
    private BigDecimal rentUnitPrice;

    /**
     * Total rent
     */
    private BigDecimal rentTotal;

    // Getters and Setters

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    @Override
    public String toString() {
        return "AssetHouse{" +
                "assetId=" + assetId +
                ", buildingArea=" + buildingArea +
                ", houseType='" + houseType + '\'' +
                ", currentUsage='" + currentUsage + '\'' +
                '}';
    }
}
