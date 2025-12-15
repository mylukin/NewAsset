package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Asset Code Sequence entity
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_asset_code_seq")
public class AssetCodeSeq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Project ID
     */
    @TableField("project_id")
    private Long projectId;

    /**
     * Asset type
     */
    @TableField("asset_type")
    private String assetType;

    /**
     * Code prefix
     */
    @TableField("prefix")
    private String prefix;

    /**
     * Current sequence number
     */
    @TableField("current_seq")
    private Long currentSeq;

    /**
     * Optimistic lock version
     */
    @Version
    @TableField("version")
    private Integer version;

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

    /**
     * Updater
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * Update time
     */
    @TableField("update_time")
    private java.time.LocalDateTime updateTime;
}
