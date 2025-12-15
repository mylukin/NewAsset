---
id: maintenance.entity
module: maintenance
priority: 702
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - maintenance.database-schema
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Work Order Entity and Mapper

## Context

Java entity classes for work order management.

## Acceptance Criteria

1. Create `MaintOrder` entity in `com.ruoyi.asset.domain.entity`:
2. Create `MaintLog` entity:
3. Create `MaintOrderMapper` interface in `com.ruoyi.asset.mapper`:
4. Create `MaintLogMapper` interface:
5. Create mapper XML files:
6. Create DTO/VO classes:
7. All entities compile without errors
## Technical Notes

- Join with t_asset for asset code/name in list
- Join with sys_user for requester/handler names
