---
id: core.state-machine
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-status-enum
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Implement Asset State Machine Service

## Acceptance Criteria

1. Create `AssetStatusService` in `com.ruoyi.asset.service.rule`:
2. Define valid state transitions matrix:
3. Create `AssetStatusChangeLog` entity for tracking changes:
4. Implement `AssetStatusChangeLogMapper` for persistence
5. Throw `ServiceException` for invalid transitions with clear error message
