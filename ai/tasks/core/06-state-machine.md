---
id: core.state-machine
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.asset-status-enum, core.asset-base-entity]
supersedes: []
tags: [backend, service]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Asset State Machine Service

## Context

The asset state machine controls valid state transitions and integrates with maintenance workflows to automatically update asset status.

## Acceptance Criteria

1. Create `AssetStatusService` in `com.ruoyi.asset.service.rule`:
   - `changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId)`
   - Validate transition is allowed (e.g., SCRAPPED cannot transition back to AVAILABLE)
   - Update `t_asset.status`
   - Record status change in audit log

2. Define valid state transitions matrix:
   - UNDER_CONSTRUCTION -> AVAILABLE_*, TO_BE_SCRAPPED
   - AVAILABLE_* -> AVAILABLE_*, TEMP_CLOSED, FAULT, MAINTAINING, TO_BE_SCRAPPED
   - TEMP_CLOSED -> AVAILABLE_*, FAULT, MAINTAINING
   - FAULT -> MAINTAINING, TO_BE_SCRAPPED
   - MAINTAINING -> AVAILABLE_*, FAULT, TO_BE_SCRAPPED
   - TO_BE_SCRAPPED -> SCRAPPED
   - SCRAPPED -> (no transitions allowed)

3. Create `AssetStatusChangeLog` entity for tracking changes:
   - asset_id, from_status, to_status, reason, operator_id, op_time

4. Implement `AssetStatusChangeLogMapper` for persistence

5. Throw `ServiceException` for invalid transitions with clear error message

## Technical Notes

- Reference: TECH.md section 5.2
- Pattern: State machine with transition validation
