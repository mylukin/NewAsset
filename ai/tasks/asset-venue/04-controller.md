---
id: asset-venue.controller
module: asset-venue
priority: 504
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-venue.service
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/asset-venue/**/*.test.*
---
# Implement Venue Asset REST API

## Context

REST controller for venue asset operations.

## Acceptance Criteria

1. Create `AssetVenueController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add RuoYi permission annotations:
4. Add @Log annotations for audit logging
5. Request validation with @Validated
6. All endpoints return AjaxResult
## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
