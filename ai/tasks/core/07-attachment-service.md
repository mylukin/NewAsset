---
id: core.attachment-service
module: core
priority: 107
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Implement Attachment Management Service

## Context

File upload and management for assets. Supports images, PDFs, and documents with size and count limits.

## Acceptance Criteria

1. Create `AssetAttachment` entity in `com.ruoyi.asset.domain.entity`:
   - Fields: id, assetId, fileName, filePath, fileType, fileSize, uploadBy, uploadTime, remark

2. Create `AssetAttachmentMapper` interface:
   - selectByAssetId(Long assetId)
   - insert, delete

3. Create `AssetAttachmentService` in `com.ruoyi.asset.service`:
   - `upload(Long assetId, MultipartFile file)`: Upload and save attachment
   - `getByAssetId(Long assetId)`: Get all attachments for asset
   - `delete(Long attachmentId)`: Delete attachment and file
   - `getFileContent(Long attachmentId)`: Get file for download

4. Enforce upload limits:
   - Max 10 files per asset
   - Max 5MB per file
   - Allowed types: jpg, jpeg, png, gif, pdf, doc, docx, xls, xlsx

5. File storage:
   - Use RuoYi default file upload path (profile/upload/)
   - Generate unique filename: {assetId}_{timestamp}_{originalName}
   - Store relative path in database

6. Validation errors:
   - File too large: "File size exceeds 5MB limit"
   - Too many files: "Asset already has 10 attachments"
   - Invalid type: "File type not allowed: {type}"

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (10 files, 5MB each)
- UX: ai/tasks/spec/UX-DESIGN.md (Attachment tab in detail drawer)
- Use Thumbnailator for image thumbnail generation (optional)
