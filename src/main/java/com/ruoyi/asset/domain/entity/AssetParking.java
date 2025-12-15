package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * AssetParking entity class
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_asset_parking")
public class AssetParking implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK to t_asset.id)
     */
    @TableId(value = "asset_id", type = IdType.INPUT)
    private Long assetId;

    /**
     * 车位编号 (Parking space number)
     */
    @TableField("parking_no")
    private String parkingNo;

    /**
     * 车库区域 (Parking zone)
     */
    @TableField("parking_zone")
    private String parkingZone;

    /**
     * 车位类型 (fixed, temporary, reserved)
     */
    @TableField("parking_type")
    private String parkingType;

    /**
     * 车位面积 (Parking space area in sqm)
     */
    @TableField("area")
    private BigDecimal area;

    /**
     * 当前使用人 (Current user)
     */
    @TableField("current_user")
    private String currentUser;

    /**
     * 车牌号 (License plate number)
     */
    @TableField("plate_no")
    private String plateNo;

    /**
     * 租金/包月金额 (Rent price)
     */
    @TableField("rent_price")
    private BigDecimal rentPrice;

    /**
     * 合同编号 (Contract number)
     */
    @TableField("contract_no")
    private String contractNo;

    /**
     * Creator
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * Creation time
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    /**
     * Updater
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * Update time
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
