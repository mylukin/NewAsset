---
id: core.attachment-service
module: core
priority: 7
status: failing
version: 9
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - service
  - file-upload
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Create Asset Attachment Service

## Context

Assets and maintenance orders support file attachments (photos, contracts, documents). A unified attachment service handles upload, storage, and retrieval.

## Acceptance Criteria

1. Create `AssetAttachment` entity mapping `t_asset_attachment`
2. Create `AssetAttachmentMapper` for CRUD operations
3. Create `AssetAttachmentService` with methods:
   - `upload(Long assetId, MultipartFile file)` - upload and save attachment
   - `list(Long assetId)` - get all attachments for an asset
   - `download(Long attachmentId)` - retrieve file for download
   - `delete(Long attachmentId)` - soft delete attachment
4. Integrate with RuoYi file upload infrastructure
5. Support multiple file types (images, PDF, documents)
6. Store file metadata: fileName, filePath, fileType, fileSize, uploadBy, uploadTime
7. Add file size and type validation

## Technical Notes

- Reference: PRD Section 4.5, RuoYi file upload components
- Pattern: Service + Mapper pattern
- Storage: Use RuoYi configured file storage path
- Location: `com.ruoyi.asset.service.AssetAttachmentService`
