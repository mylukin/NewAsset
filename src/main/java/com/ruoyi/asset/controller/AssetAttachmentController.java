package com.ruoyi.asset.controller;

import com.ruoyi.asset.domain.entity.AssetAttachment;
import com.ruoyi.asset.service.AssetAttachmentService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.PreAuthorize;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.file.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * Asset Attachment Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@RestController
@RequestMapping("/asset/attachment")
@Validated
@RequiredArgsConstructor
public class AssetAttachmentController extends BaseController {

    private final AssetAttachmentService assetAttachmentService;

    /**
     * Upload attachment
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Log(title = "Attachment", businessType = "ADD")
    @PreAuthorize(hasPermi = "asset:attachment:add")
    public AjaxResult upload(@RequestPart("file") @NotNull MultipartFile file,
                            @RequestParam @NotNull Long assetId) {
        try {
            AssetAttachment attachment = assetAttachmentService.upload(assetId, file, getUserId());
            return AjaxResult.success("Upload successful", attachment);
        } catch (Exception e) {
            log.error("File upload failed", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * List attachments by asset ID
     */
    @GetMapping("/list/{assetId}")
    @PreAuthorize(hasPermi = "asset:attachment:list")
    public AjaxResult list(@PathVariable Long assetId) {
        List<AssetAttachment> attachments = assetAttachmentService.getByAssetId(assetId);
        return AjaxResult.success(attachments);
    }

    /**
     * Delete attachment
     */
    @DeleteMapping("/{id}")
    @Log(title = "Attachment", businessType = "REMOVE")
    @PreAuthorize(hasPermi = "asset:attachment:remove")
    public AjaxResult delete(@PathVariable Long id) {
        try {
            boolean result = assetAttachmentService.delete(id);
            return result ? AjaxResult.success("Deleted successfully") : AjaxResult.error("Deletion failed");
        } catch (Exception e) {
            log.error("Failed to delete attachment", e);
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * Download attachment
     */
    @GetMapping("/download/{id}")
    @PreAuthorize(hasPermi = "asset:attachment:query")
    public void download(@PathVariable Long id, HttpServletResponse response) {
        try {
            AssetAttachment attachment = assetAttachmentService.getById(id);
            if (attachment == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String filePath = attachment.getFilePath();
            String fileName = attachment.getFileName();

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtils.setFileDownloadHeader(response, fileName));

            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (IOException e) {
            log.error("File download failed", e);
            try {
                response.getWriter().write("File download failed: " + e.getMessage());
            } catch (IOException ioException) {
                log.error("Failed to write error response", ioException);
            }
        }
    }

    /**
     * Get attachment info
     */
    @GetMapping("/{id}")
    @PreAuthorize(hasPermi = "asset:attachment:query")
    public AjaxResult getInfo(@PathVariable Long id) {
        AssetAttachment attachment = assetAttachmentService.getById(id);
        return AjaxResult.success(attachment);
    }
}
