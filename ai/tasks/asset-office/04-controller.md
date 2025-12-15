---
id: asset-office.controller
module: asset-office
priority: 604
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-office.service
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/asset-office/**/*.test.*
---
# Implement Office Asset REST API

## Context

REST controller for office asset operations.

## Acceptance Criteria

1. Create `AssetOfficeController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add RuoYi permission annotations:
4. Add @Log annotations for audit logging
5. Request validation with @Validated
6. All endpoints return AjaxResult
## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
