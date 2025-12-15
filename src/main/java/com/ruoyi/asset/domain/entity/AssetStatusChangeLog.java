package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Asset Status Change Log entity
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_asset_status_change_log")
public class AssetStatusChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset ID
     */
    @TableField("asset_id")
    private Long assetId;

    /**
     * From status
     */
    @TableField("from_status")
    private String fromStatus;

    /**
     * To status
     */
    @TableField("to_status")
    private String toStatus;

    /**
     * Operator user ID
     */
    @TableField("operator_id")
    private Long operatorId;

    /**
     * Change reason
     */
    @TableField("reason")
    private String reason;

    /**
     * Change timestamp
     */
    @TableField("change_time")
    private java.time.LocalDateTime changeTime;

    /**
     * Creator
     */
    @TableField("create_by")
    private String createBy;

    /**
     * Creation time
     */
    @TableField("create_time")
    private java.time.LocalDateTime createTime;
}
