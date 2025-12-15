package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Asset Location entity
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_asset_location")
public class AssetLocation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Parent location ID (0 for root)
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * Location name
     */
    @TableField("name")
    private String name;

    /**
     * Location code
     */
    @TableField("code")
    private String code;

    /**
     * Hierarchy level (1=Building, 2=Floor, 3=Unit)
     */
    @TableField("level")
    private Integer level;

    /**
     * Display sort order
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * Project ID
     */
    @TableField("project_id")
    private Long projectId;

    /**
     * Status (0=normal, 1=disabled)
     */
    @TableField("status")
    private String status;

    /**
     * Delete flag (0=exists, 2=deleted)
     */
    @TableLogic
    @TableField("del_flag")
    private String delFlag;
}
