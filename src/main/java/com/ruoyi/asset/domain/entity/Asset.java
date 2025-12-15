package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Asset entity class
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_asset")
public class Asset extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset code (unique, auto-generated)
     */
    @TableField("asset_code")
    private String assetCode;

    /**
     * Asset name
     */
    @TableField("name")
    private String name;

    /**
     * Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
     */
    @TableField("asset_type")
    private String assetType;

    /**
     * Asset status (9-state machine)
     */
    @TableField("status")
    private String status;

    /**
     * Project ID
     */
    @TableField("project_id")
    private Long projectId;

    /**
     * Building ID (level 1 location)
     */
    @TableField("building_id")
    private Long buildingId;

    /**
     * Floor ID (level 2 location)
     */
    @TableField("floor_id")
    private Long floorId;

    /**
     * Unit ID (level 3 location)
     */
    @TableField("unit_id")
    private Long unitId;

    /**
     * Location description text
     */
    @TableField("location_desc")
    private String locationDesc;

    /**
     * Responsible person name
     */
    @TableField("responsible_person")
    private String responsiblePerson;

    /**
     * Responsible person phone
     */
    @TableField("responsible_phone")
    private String responsiblePhone;

    /**
     * Purchase/acquisition date
     */
    @TableField("purchase_date")
    private LocalDate purchaseDate;

    /**
     * Purchase price
     */
    @TableField("purchase_price")
    private BigDecimal purchasePrice;

    /**
     * Warranty expiration date
     */
    @TableField("warranty_expire_date")
    private LocalDate warrantyExpireDate;

    /**
     * Asset description
     */
    @TableField("description")
    private String description;

    /**
     * Last status change time
     */
    @TableField("status_changed_at")
    private LocalDateTime statusChangedAt;

    /**
     * Last status change operator
     */
    @TableField("status_changed_by")
    private String statusChangedBy;

    /**
     * Delete flag (0=exists, 2=deleted)
     */
    @TableLogic
    @TableField("del_flag")
    private String delFlag;
}
