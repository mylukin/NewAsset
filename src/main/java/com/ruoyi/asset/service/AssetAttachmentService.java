package com.ruoyi.asset.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.asset.domain.entity.AssetAttachment;
import com.ruoyi.asset.mapper.AssetAttachmentMapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Asset Attachment Service
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetAttachmentService extends ServiceImpl<AssetAttachmentMapper, AssetAttachment> {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_FILES_PER_ASSET = 10;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
        "jpg", "jpeg", "png", "gif", "pdf", "doc", "docx", "xls", "xlsx"
    );

    /**
     * Upload attachment for asset
     */
    @Transactional(rollbackFor = Exception.class)
    public AssetAttachment upload(Long assetId, MultipartFile file, String operatorId) {
        validateFile(file);

        // Check max files per asset
        int currentCount = baseMapper.selectCount(
            new LambdaQueryWrapper<AssetAttachment>()
                .eq(AssetAttachment::getAssetId, assetId)
                .eq(AssetAttachment::getDelFlag, "0")
        );

        if (currentCount >= MAX_FILES_PER_ASSET) {
            throw new ServiceException("Asset already has " + MAX_FILES_PER_ASSET + " attachments");
        }

        try {
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String uniqueFilename = String.format("%d_%d_%s.%s",
                assetId,
                System.currentTimeMillis(),
                UUID.randomUUID().toString().substring(0, 8),
                extension);

            // Upload file
            String relativePath = FileUploadUtils.upload(uniqueFilename, file);

            // Save to database
            AssetAttachment attachment = new AssetAttachment();
            attachment.setAssetId(assetId);
            attachment.setFileName(originalFilename);
            attachment.setFilePath(relativePath);
            attachment.setFileSize(file.getSize());
            attachment.setFileType(file.getContentType());
            attachment.setSortOrder(currentCount + 1);
            attachment.setCreateBy(operatorId);
            attachment.setCreateTime(LocalDateTime.now());

            baseMapper.insert(attachment);
            return attachment;
        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new ServiceException("File upload failed: " + e.getMessage());
        }
    }

    /**
     * Get all attachments for asset
     */
    public List<AssetAttachment> getByAssetId(Long assetId) {
        return baseMapper.selectByAssetId(assetId);
    }

    /**
     * Delete attachment
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long attachmentId) {
        AssetAttachment attachment = baseMapper.selectById(attachmentId);
        if (attachment == null) {
            return false;
        }

        // Soft delete
        attachment.setDelFlag("2");
        return baseMapper.updateById(attachment) > 0;
    }

    /**
     * Validate file
     */
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServiceException("File is empty");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ServiceException("File size exceeds 5MB limit");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);

        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new ServiceException("File type not allowed: " + extension);
        }
    }

    /**
     * Get file extension
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf('.') == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
