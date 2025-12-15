---
id: core.attachment-controller
module: core
priority: 110
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.attachment-service
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Implement Attachment Upload API

## Context

REST API for file upload and management. Used in asset detail attachment tab.

## Acceptance Criteria

1. Create `AssetAttachmentController` in `com.ruoyi.asset.controller`:
   - Base path: `/asset/attachment`

2. Implement endpoints:

   **POST /asset/attachment/upload**
   - Multipart form: file (required), assetId (required)
   - Returns: Uploaded attachment info
   - Permission: asset:attachment:add
   ```json
   {
     "code": 200,
     "data": {
       "id": 1,
       "fileName": "photo.jpg",
       "filePath": "/upload/2024/01/15/xxx.jpg",
       "fileSize": 1024000,
       "fileType": "image/jpeg"
     }
   }
   ```

   **GET /asset/attachment/list/{assetId}**
   - Returns: List of attachments for asset
   - Permission: authenticated users

   **DELETE /asset/attachment/{id}**
   - Returns: Success/failure
   - Permission: asset:attachment:remove

   **GET /asset/attachment/download/{id}**
   - Returns: File stream for download
   - Sets Content-Disposition header

3. Error responses:
   - 400: "File size exceeds 5MB limit"
   - 400: "Asset already has 10 attachments"
   - 400: "File type not allowed"
   - 404: "Attachment not found"

4. Add @Log annotation for upload and delete

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Upload limits)
- UX: ai/tasks/spec/UX-DESIGN.md (Drag-drop upload zone)
