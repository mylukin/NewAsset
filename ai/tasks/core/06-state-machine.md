---
id: core.state-machine
module: core
priority: 6
status: failing
version: 9
origin: manual
dependsOn:
  - core.asset-status-enum
  - core.asset-base-entity
supersedes: []
tags:
  - service
  - state-machine
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Implement Asset Status State Machine Service

## Context

Asset status changes must follow defined rules. Invalid transitions should be rejected. Status changes from maintenance workflow must be coordinated.

## Acceptance Criteria

1. Create `AssetStatusService` in `com.ruoyi.asset.service.rule`
2. Implement `changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId)`
3. Define valid state transitions (e.g., SCRAPPED cannot transition back to AVAILABLE)
4. Reject invalid transitions with meaningful error messages
5. Log all status changes with before/after values
6. Support status change triggered by maintenance workflow
7. Optional: Create `t_asset_status_log` for status change audit trail
8. Integrate with maintenance order completion to restore original status

## Technical Notes

- Reference: TECH.md Section 5.2
- Pattern: State machine with transition validation
- Integration: Called by AssetService and MaintOrderService
- Location: `com.ruoyi.asset.service.rule.AssetStatusService`
