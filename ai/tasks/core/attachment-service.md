---
id: core.attachment-service
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Implement Asset Attachment Service

## Acceptance Criteria

1. Create `AssetAttachment` entity mapping `t_asset_attachment`:
2. Create `AssetAttachmentMapper` with CRUD operations
3. Create `AssetAttachmentService`:
4. Create `AssetAttachmentController`:
5. Integrate with RuoYi's file storage configuration
6. Support multiple file upload
7. Validate file types and sizes (configurable limits)
