package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Asset Attachment entity
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_asset_attachment")
public class AssetAttachment implements Serializable {

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
     * Original file name
     */
    @TableField("file_name")
    private String fileName;

    /**
     * Storage file path
     */
    @TableField("file_path")
    private String filePath;

    /**
     * File size in bytes
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * File MIME type
     */
    @TableField("file_type")
    private String fileType;

    /**
     * Display sort order
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * Delete flag (0=exists, 2=deleted)
     */
    @TableLogic
    @TableField("del_flag")
    private String delFlag;

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
