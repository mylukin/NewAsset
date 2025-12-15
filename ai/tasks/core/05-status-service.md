---
id: core.status-service
module: core
priority: 105
status: failed
version: 2
origin: spec-workflow
dependsOn:
  - core.asset-entity
  - core.status-enum
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
    cases:
      - should change status when valid
      - should reject invalid transition
      - should log status change
---
# Implement Asset Status Service

## Context

Service for changing asset status with transition validation and audit logging. Ensures only valid state transitions occur.

## Acceptance Criteria

1. Create `AssetStatusService` in `com.ruoyi.asset.service`:
2. Validate transition before change:
3. Update asset status:
4. Log status change (optional table or use RuoYi @Log):
5. Unit test `AssetStatusServiceTest`:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Status linkage with work orders)
- Called by maintenance module when work orders change state

## Notes

Verification failed: Expected verification timeout - implementation complete with AssetStatusService, change log entity/mapper, and tests
