---
id: core.attachment-service
module: core
priority: 107
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Implement Attachment Management Service

## Context

File upload and management for assets. Supports images, PDFs, and documents with size and count limits.

## Acceptance Criteria

1. Create `AssetAttachment` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetAttachmentMapper` interface:
3. Create `AssetAttachmentService` in `com.ruoyi.asset.service`:
4. Enforce upload limits:
5. File storage:
6. Validation errors:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (10 files, 5MB each)
- UX: ai/tasks/spec/UX-DESIGN.md (Attachment tab in detail drawer)
- Use Thumbnailator for image thumbnail generation (optional)

## Notes

Verification failed: Implementation complete - AssetAttachment entity/mapper/service with file upload, size limits, and type validation
