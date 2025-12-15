package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * AssetHouse entity class
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_asset_house")
public class AssetHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Asset ID (FK to t_asset.id)
     */
    @TableId(value = "asset_id", type = IdType.INPUT)
    private Long assetId;

    /**
     * 建筑面积 (Building area in sqm)
     */
    @TableField("building_area")
    private BigDecimal buildingArea;

    /**
     * 套内面积 (Inner usable area in sqm)
     */
    @TableField("inner_area")
    private BigDecimal innerArea;

    /**
     * 户型 (one_room, two_room, three_room, etc.)
     */
    @TableField("house_type")
    private String houseType;

    /**
     * 房屋用途 (residential, commercial, apartment)
     */
    @TableField("house_usage")
    private String houseUsage;

    /**
     * 当前用途 (self_use, rented, idle)
     */
    @TableField("current_usage")
    private String currentUsage;

    /**
     * 当前使用人/承租人 (Current user/tenant)
     */
    @TableField("current_user")
    private String currentUser;

    /**
     * 合同编号 (Contract number)
     */
    @TableField("contract_no")
    private String contractNo;

    /**
     * 租金单价 (Rent unit price)
     */
    @TableField("rent_unit_price")
    private BigDecimal rentUnitPrice;

    /**
     * 租金总额 (Total rent)
     */
    @TableField("rent_total")
    private BigDecimal rentTotal;

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
