---
id: core.attachment-controller
module: core
priority: 110
status: failing
version: 3
origin: spec-workflow
dependsOn:
  - core.attachment-service
supersedes: []
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Implement Attachment Upload API

## Context

REST API for file upload and management. Used in asset detail attachment tab.

## Acceptance Criteria

1. Create `AssetAttachmentController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Error responses:
4. Add @Log annotation for upload and delete
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Upload limits)
- UX: ai/tasks/spec/UX-DESIGN.md (Drag-drop upload zone)

## Notes

Verification failed: Implementation complete - AssetAttachmentController with upload, list, delete, download endpoints
