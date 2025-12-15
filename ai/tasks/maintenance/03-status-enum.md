---
id: maintenance.status-enum
module: maintenance
priority: 703
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - maintenance.entity
supersedes: []
tags:
  - backend
  - enum
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create 6-State Work Order Status Enum

## Context

Work order status enum with transition validation.

## Acceptance Criteria

1. Create `MaintOrderStatus` enum in `com.ruoyi.asset.domain.enums`:
2. Implement valid transition rules:
3. Create `MaintOrderStatusMachine` class:
4. Add unit tests for all transition rules
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (6-state flow)
- PRD: docs/PRD.md Section 6.1.2 (状态流转)
