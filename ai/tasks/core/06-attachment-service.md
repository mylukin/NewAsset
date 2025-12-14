---
id: core.attachment-service
module: core
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [infrastructure, file-upload]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Asset Attachment Service

## Context

All assets and maintenance orders support file attachments (photos, contracts, documents). This service provides unified attachment management.

## Acceptance Criteria

1. `AssetAttachment` entity created:
   - id, assetId, fileName, filePath, fileType, fileSize
   - uploadBy, uploadTime, remark
2. `AssetAttachmentService` implemented:
   - `upload(assetId, MultipartFile[])` - batch upload
   - `list(assetId)` - get all attachments for asset
   - `delete(attachmentId)` - remove attachment
   - `download(attachmentId)` - get file stream
3. REST API endpoints:
   - `POST /asset/attachment/{assetId}` - upload files
   - `GET /asset/attachment/{assetId}` - list attachments
   - `DELETE /asset/attachment/{id}` - delete attachment
   - `GET /asset/attachment/download/{id}` - download file
4. Frontend components:
   - File upload component with drag-drop support
   - Attachment list with preview and download
   - Image preview in lightbox
5. File storage:
   - Use RuoYi's existing file upload mechanism
   - Support configurable storage path

## Technical Notes

- Leverage RuoYi's `SysFileController` patterns
- Max file size: configurable (default 10MB)
- Supported types: images, PDF, Office documents
