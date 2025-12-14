package com.ruoyi.asset.service.impl;

import com.ruoyi.asset.domain.entity.AssetAttachment;
import com.ruoyi.asset.mapper.AssetAttachmentMapper;
import com.ruoyi.asset.service.AssetAttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Asset attachment service implementation
 * Integrates with RuoYi file upload infrastructure
 */
@Service
public class AssetAttachmentServiceImpl implements AssetAttachmentService {

    private static final Logger log = LoggerFactory.getLogger(AssetAttachmentServiceImpl.class);

    /**
     * Maximum file size (default 10MB)
     */
    @Value("${asset.attachment.max-size:10485760}")
    private long maxFileSize;

    /**
     * Upload base path
     */
    @Value("${ruoyi.profile:./uploadPath}")
    private String uploadPath;

    /**
     * Allowed file types (MIME types)
     */
    private static final Set<String> ALLOWED_TYPES = new HashSet<>(Arrays.asList(
            // Images
            "image/png",
            "image/jpeg",
            "image/jpg",
            "image/gif",
            "image/bmp",
            "image/webp",
            // Documents
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            // Text
            "text/plain",
            "text/csv",
            // Archives
            "application/zip",
            "application/x-rar-compressed"
    ));

    /**
     * Allowed file extensions
     */
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
            "png", "jpg", "jpeg", "gif", "bmp", "webp",
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "txt", "csv", "zip", "rar"
    ));

    @Autowired
    private AssetAttachmentMapper attachmentMapper;

    @Override
    public AssetAttachment uploadAttachment(Long assetId, MultipartFile file, String uploadBy, String remark) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        // Validate file type
        String contentType = file.getContentType();
        if (!isAllowedFileType(contentType)) {
            throw new IllegalArgumentException("File type not allowed: " + contentType);
        }

        // Validate file size
        if (!isFileSizeValid(file.getSize())) {
            throw new IllegalArgumentException("File size exceeds maximum limit of " + (maxFileSize / 1024 / 1024) + "MB");
        }

        // Generate unique file name
        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;

        // Create directory structure: uploadPath/asset/{assetId}/{year}/{month}/
        String datePath = String.format("%tY/%tm", new Date(), new Date());
        String relativePath = String.format("asset/%d/%s", assetId, datePath);
        String fullDirPath = uploadPath + File.separator + relativePath;

        try {
            // Create directories if not exist
            Path dirPath = Paths.get(fullDirPath);
            Files.createDirectories(dirPath);

            // Save file
            Path filePath = dirPath.resolve(uniqueFileName);
            file.transferTo(filePath.toFile());

            // Create attachment record
            AssetAttachment attachment = new AssetAttachment();
            attachment.setAssetId(assetId);
            attachment.setFileName(originalFilename);
            attachment.setFilePath(relativePath + "/" + uniqueFileName);
            attachment.setFileType(getFileTypeFromExtension(extension));
            attachment.setFileSize(file.getSize());
            attachment.setUploadBy(uploadBy);
            attachment.setUploadTime(new Date());
            attachment.setRemark(remark);

            attachmentMapper.insert(attachment);
            log.info("Uploaded attachment {} for asset {}", originalFilename, assetId);

            return attachment;
        } catch (IOException e) {
            log.error("Failed to upload attachment for asset {}", assetId, e);
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    @Override
    public List<AssetAttachment> getAttachmentsByAssetId(Long assetId) {
        return attachmentMapper.findByAssetId(assetId);
    }

    @Override
    public AssetAttachment getById(Long id) {
        return attachmentMapper.selectById(id);
    }

    @Override
    public boolean deleteAttachment(Long id) {
        AssetAttachment attachment = attachmentMapper.selectById(id);
        if (attachment == null) {
            return false;
        }

        // Delete file from disk
        try {
            Path filePath = Paths.get(uploadPath, attachment.getFilePath());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.warn("Failed to delete attachment file: {}", attachment.getFilePath(), e);
        }

        // Delete database record
        int result = attachmentMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public int deleteByAssetId(Long assetId) {
        // Get all attachments first to delete files
        List<AssetAttachment> attachments = attachmentMapper.findByAssetId(assetId);
        for (AssetAttachment attachment : attachments) {
            try {
                Path filePath = Paths.get(uploadPath, attachment.getFilePath());
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.warn("Failed to delete attachment file: {}", attachment.getFilePath(), e);
            }
        }

        // Delete database records
        return attachmentMapper.deleteByAssetId(assetId);
    }

    @Override
    public boolean isAllowedFileType(String contentType) {
        if (contentType == null) {
            return false;
        }
        return ALLOWED_TYPES.contains(contentType.toLowerCase());
    }

    @Override
    public boolean isFileSizeValid(long fileSize) {
        return fileSize > 0 && fileSize <= maxFileSize;
    }

    @Override
    public String getDownloadPath(Long id) {
        AssetAttachment attachment = attachmentMapper.selectById(id);
        if (attachment == null) {
            return null;
        }
        return uploadPath + File.separator + attachment.getFilePath();
    }

    /**
     * Get file extension from filename
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf('.') == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }

    /**
     * Get file type category from extension
     */
    private String getFileTypeFromExtension(String extension) {
        if (extension == null) {
            return "OTHER";
        }
        extension = extension.toLowerCase();
        if (Arrays.asList("png", "jpg", "jpeg", "gif", "bmp", "webp").contains(extension)) {
            return "IMAGE";
        } else if (Arrays.asList("pdf").contains(extension)) {
            return "PDF";
        } else if (Arrays.asList("doc", "docx").contains(extension)) {
            return "WORD";
        } else if (Arrays.asList("xls", "xlsx").contains(extension)) {
            return "EXCEL";
        } else if (Arrays.asList("ppt", "pptx").contains(extension)) {
            return "PPT";
        } else if (Arrays.asList("txt", "csv").contains(extension)) {
            return "TEXT";
        } else if (Arrays.asList("zip", "rar").contains(extension)) {
            return "ARCHIVE";
        }
        return "OTHER";
    }
}
