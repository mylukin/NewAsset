package com.ruoyi.asset.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Asset attachment entity
 * Maps to t_asset_attachment table
 */
@TableName("t_asset_attachment")
public class AssetAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Asset ID (FK)
     */
    @NotNull(message = "Asset ID cannot be null")
    private Long assetId;

    /**
     * File name
     */
    @NotBlank(message = "File name cannot be blank")
    @Size(max = 200, message = "File name must be at most 200 characters")
    private String fileName;

    /**
     * File path
     */
    @NotBlank(message = "File path cannot be blank")
    @Size(max = 500, message = "File path must be at most 500 characters")
    private String filePath;

    /**
     * File type (MIME type or extension)
     */
    @Size(max = 32, message = "File type must be at most 32 characters")
    private String fileType;

    /**
     * File size in bytes
     */
    private Long fileSize;

    /**
     * Uploader username
     */
    @Size(max = 64, message = "Upload by must be at most 64 characters")
    private String uploadBy;

    /**
     * Upload time
     */
    private Date uploadTime;

    /**
     * Remark
     */
    @Size(max = 500, message = "Remark must be at most 500 characters")
    private String remark;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AssetAttachment{" +
                "id=" + id +
                ", assetId=" + assetId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
