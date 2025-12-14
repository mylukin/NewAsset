---
id: core.attachment-service
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.database-schema]
supersedes: []
tags: [backend, service]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Asset Attachment Service

## Context

Assets and maintenance orders support file attachments (photos, contracts, documents). This service handles upload, download, and management of these files.

## Acceptance Criteria

1. Create `AssetAttachment` entity mapping `t_asset_attachment`:
   - id, asset_id, file_name, file_path, file_type, file_size
   - upload_by, upload_time, remark

2. Create `AssetAttachmentMapper` with CRUD operations

3. Create `AssetAttachmentService`:
   - `upload(Long assetId, MultipartFile file)` - save file and create record
   - `download(Long attachmentId)` - return file stream
   - `delete(Long attachmentId)` - soft delete attachment
   - `listByAssetId(Long assetId)` - get all attachments for an asset

4. Create `AssetAttachmentController`:
   - `POST /asset/attachment/upload` - upload file
   - `GET /asset/attachment/{id}` - download file
   - `DELETE /asset/attachment/{id}` - delete attachment
   - `GET /asset/attachment/list/{assetId}` - list attachments

5. Integrate with RuoYi's file storage configuration
6. Support multiple file upload
7. Validate file types and sizes (configurable limits)

## Technical Notes

- Reference: PRD section 4.5
- Use RuoYi's existing file upload utilities
