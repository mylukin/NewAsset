---
id: core.location-controller
module: core
priority: 109
status: failed
version: 2
origin: spec-workflow
dependsOn:
  - core.location-service
supersedes: []
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Implement Location Hierarchy API

## Context

REST API for location hierarchy management. Supports cascader component for building/floor/unit selection.

## Acceptance Criteria

1. Create `AssetLocationController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add @Log annotation for audit logging
4. Validation:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (3-level hierarchy)
- UX: Cascader needs tree structure response

## Notes

Verification failed: Implementation complete - AssetLocationController with tree, create, update, delete endpoints and DTOs
