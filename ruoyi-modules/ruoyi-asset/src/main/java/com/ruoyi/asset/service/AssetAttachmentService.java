package com.ruoyi.asset.service;

import com.ruoyi.asset.domain.entity.AssetAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Asset attachment service interface
 */
public interface AssetAttachmentService {

    /**
     * Upload attachment for an asset
     *
     * @param assetId  Asset ID
     * @param file     Uploaded file
     * @param uploadBy Uploader username
     * @param remark   Optional remark
     * @return Saved attachment entity
     */
    AssetAttachment uploadAttachment(Long assetId, MultipartFile file, String uploadBy, String remark);

    /**
     * Get attachments by asset ID
     *
     * @param assetId Asset ID
     * @return List of attachments
     */
    List<AssetAttachment> getAttachmentsByAssetId(Long assetId);

    /**
     * Get attachment by ID
     *
     * @param id Attachment ID
     * @return Attachment or null
     */
    AssetAttachment getById(Long id);

    /**
     * Delete attachment by ID
     *
     * @param id Attachment ID
     * @return true if deleted
     */
    boolean deleteAttachment(Long id);

    /**
     * Delete all attachments for an asset
     *
     * @param assetId Asset ID
     * @return Number of deleted attachments
     */
    int deleteByAssetId(Long assetId);

    /**
     * Check if file type is allowed
     *
     * @param contentType MIME type
     * @return true if allowed
     */
    boolean isAllowedFileType(String contentType);

    /**
     * Check if file size is within limit
     *
     * @param fileSize File size in bytes
     * @return true if within limit
     */
    boolean isFileSizeValid(long fileSize);

    /**
     * Get the download path for an attachment
     *
     * @param id Attachment ID
     * @return File path for download
     */
    String getDownloadPath(Long id);
}
