---
id: maintenance.controller
module: maintenance
priority: 707
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.order-service
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Implement Work Order REST API

## Context

REST controller for work order operations.

## Acceptance Criteria

1. Create `MaintOrderController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add permission annotations:
4. Add @Log annotations for all write operations
5. Request validation with @Validated
6. Role-based operation validation:
## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level permissions
