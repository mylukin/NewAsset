---
id: asset-facility.controller
module: asset-facility
priority: 404
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.service
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/asset-facility/**/*.test.*
---
# Implement Facility Asset REST API

## Context

REST controller for facility asset operations.

## Acceptance Criteria

1. Create `AssetFacilityController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add RuoYi permission annotations:
4. Add @Log annotations for audit logging
5. Request validation with @Validated
6. All endpoints return AjaxResult
## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
