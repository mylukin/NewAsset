---
id: core.status-service
module: core
priority: 105
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.asset-entity
  - core.status-enum
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
    cases:
      - "should change status when valid"
      - "should reject invalid transition"
      - "should log status change"
---
# Implement Asset Status Service

## Context

Service for changing asset status with transition validation and audit logging. Ensures only valid state transitions occur.

## Acceptance Criteria

1. Create `AssetStatusService` in `com.ruoyi.asset.service`:
   - Method: `changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId)`
   - Returns updated Asset or throws ServiceException

2. Validate transition before change:
   - Get current asset status
   - Call AssetStatusEnum.canTransitionTo()
   - If invalid, throw ServiceException with message: "Invalid status transition: {from} cannot change to {to}"

3. Update asset status:
   - Update status field in t_asset
   - Update update_by and update_time

4. Log status change (optional table or use RuoYi @Log):
   - Record: asset_id, from_status, to_status, operator_id, reason, timestamp
   - Can use t_asset_change_log or rely on RuoYi operation log

5. Unit test `AssetStatusServiceTest`:
   - Test successful status change
   - Test rejection of invalid transition
   - Test status change is logged
   - Test asset not found scenario

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Status linkage with work orders)
- Called by maintenance module when work orders change state
