---
id: core.state-machine
module: core
priority: 6
status: passing
version: 16
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
verification:
  verifiedAt: '2025-12-14T12:02:52.096Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: b2e67775d09b5ab0f09c790c53918283bdf5e2c7
  summary: 8/8 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:01:28.089Z'
  generatedBy: claude
  forVersion: 9
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/service/rule/AssetStatusServiceTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetStatusService in correct package
      assertions:
        - assertNotNull(assetStatusService)
        - assertTrue(assetStatusService instanceof AssetStatusService)
    - name: should change status successfully with valid transition
      assertions:
        - 'assertEquals(AssetStatusEnum.IN_USE, result.getNewStatus())'
        - assertNotNull(result.getChangeTime())
        - 'assertEquals(operatorId, result.getOperatorId())'
    - name: should reject invalid state transition from SCRAPPED to AVAILABLE
      assertions:
        - >-
          assertThrows(InvalidStateTransitionException.class, () ->
          service.changeStatus(assetId, AssetStatusEnum.AVAILABLE, reason,
          operatorId))
        - assertTrue(exception.getMessage().contains("SCRAPPED"))
    - name: should return meaningful error message for invalid transition
      assertions:
        - assertNotNull(exception.getMessage())
        - assertTrue(exception.getMessage().contains("cannot transition"))
        - assertTrue(exception.getMessage().contains(fromStatus.name()))
        - assertTrue(exception.getMessage().contains(toStatus.name()))
    - name: should log status change with before and after values
      assertions:
        - verify(logger).info(contains("IDLE"))
        - verify(logger).info(contains("IN_USE"))
        - verify(logger).info(contains(assetId.toString()))
    - name: should support status change triggered by maintenance workflow
      assertions:
        - 'assertEquals(AssetStatusEnum.UNDER_MAINTENANCE, asset.getStatus())'
        - assertNotNull(result.getMaintenanceOrderId())
        - assertTrue(result.isTriggeredByMaintenance())
    - name: should persist status change to audit log table
      assertions:
        - verify(statusLogRepository).save(any(AssetStatusLog.class))
        - 'assertEquals(oldStatus, savedLog.getBeforeStatus())'
        - 'assertEquals(newStatus, savedLog.getAfterStatus())'
        - 'assertEquals(reason, savedLog.getReason())'
    - name: should restore original status when maintenance order completes
      assertions:
        - 'assertEquals(originalStatus, asset.getStatus())'
        - assertNotNull(restoredResult.getMaintenanceOrderId())
        - 'assertEquals("maintenance_completed", restoredResult.getTriggerType())'
  e2eScenarios: []
  frameworkHint: junit5-surefire
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
