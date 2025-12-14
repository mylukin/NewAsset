package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Asset status change log entity
 * Maps to t_asset_status_log table
 */
@TableName("t_asset_status_log")
public class AssetStatusLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset ID
     */
    private Long assetId;

    /**
     * Status before change
     */
    private String beforeStatus;

    /**
     * Status after change
     */
    private String afterStatus;

    /**
     * Change reason
     */
    private String reason;

    /**
     * Related maintenance order ID (if applicable)
     */
    private Long maintOrderId;

    /**
     * Operator user ID
     */
    private Long operatorId;

    /**
     * Change time
     */
    private Date changeTime;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getMaintOrderId() {
        return maintOrderId;
    }

    public void setMaintOrderId(Long maintOrderId) {
        this.maintOrderId = maintOrderId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "AssetStatusLog{" +
                "id=" + id +
                ", assetId=" + assetId +
                ", beforeStatus='" + beforeStatus + '\'' +
                ", afterStatus='" + afterStatus + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
