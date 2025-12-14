---
id: core.state-machine
module: core
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [domain, business-logic, critical]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Unified Asset State Machine

## Context

The asset state machine enforces valid state transitions and integrates with maintenance workflows. All asset types share this unified state model.

## Acceptance Criteria

1. `AssetStatusService` implemented with methods:
   - `changeStatus(assetId, newStatus, reason, operatorId)`
   - `canTransitionTo(currentStatus, targetStatus)` - validates transitions
   - `getAvailableTransitions(currentStatus)` - returns valid next states
2. State transition rules enforced:
   - SCRAPPED is terminal (no transitions out)
   - TO_BE_SCRAPPED can only go to SCRAPPED
   - MAINTAINING can transition back to any AVAILABLE_* state
   - FAULT can go to MAINTAINING or TO_BE_SCRAPPED
3. Status change logging:
   - Record to `t_asset_change_log` with old/new values
   - Include operator and timestamp
4. Integration points defined:
   - Hook for maintenance order status changes
   - Configurable auto-transition on work order events
5. Unit tests cover:
   - All valid state transitions
   - Invalid transition rejection
   - Concurrent status update handling
   - Change log creation

## Technical Notes

- State transitions can be configured via dictionary (sys_dict_data)
- Consider Spring Statemachine for complex workflows (optional)
- Integration with maintenance module via event/callback pattern
