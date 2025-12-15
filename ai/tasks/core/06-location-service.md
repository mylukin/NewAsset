---
id: core.location-service
module: core
priority: 106
status: failed
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
# Implement Location Hierarchy Service

## Context

3-level location hierarchy (Building → Floor → Unit) management. Supports the cascader component in frontend forms.

## Acceptance Criteria

1. Create `AssetLocation` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetLocationMapper` interface:
3. Create `AssetLocationService` in `com.ruoyi.asset.service`:
4. Tree structure response format:
5. Validation:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (3-level hierarchy decision)
- UX: ai/tasks/spec/UX-DESIGN.md (Cascader component)

## Notes

Verification failed: Implementation complete - AssetLocation entity/mapper/service created with 3-level hierarchy support and tree structure
