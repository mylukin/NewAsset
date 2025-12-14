package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Parking asset extension entity
 * Maps to t_asset_parking table
 * Links to t_asset via asset_id
 */
@TableName("t_asset_parking")
public class AssetParking implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK, also primary key)
     */
    @TableId(value = "asset_id")
    private Long assetId;

    /**
     * Parking space number
     */
    @NotBlank(message = "Parking number is required")
    @Size(max = 32, message = "Parking number must be at most 32 characters")
    private String parkingNo;

    /**
     * Parking zone/area (e.g., underground, ground)
     */
    @Size(max = 64, message = "Parking zone must be at most 64 characters")
    private String parkingZone;

    /**
     * Parking type (fixed/temporary)
     */
    @Size(max = 32, message = "Parking type must be at most 32 characters")
    private String parkingType;

    /**
     * Area in square meters
     */
    private BigDecimal area;

    /**
     * Current user/tenant name
     */
    @Size(max = 64, message = "Current user must be at most 64 characters")
    private String currentUser;

    /**
     * Vehicle plate number
     */
    @Size(max = 32, message = "Plate number must be at most 32 characters")
    private String plateNo;

    /**
     * Rent price (monthly)
     */
    private BigDecimal rentPrice;

    /**
     * Contract number reference
     */
    @Size(max = 64, message = "Contract number must be at most 64 characters")
    private String contractNo;

    // Getters and Setters

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Override
    public String toString() {
        return "AssetParking{" +
                "assetId=" + assetId +
                ", parkingNo='" + parkingNo + '\'' +
                ", parkingZone='" + parkingZone + '\'' +
                ", parkingType='" + parkingType + '\'' +
                ", area=" + area +
                '}';
    }
}
