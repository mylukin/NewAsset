---
id: maintenance.status-enum
module: maintenance
priority: 703
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.entity
tags:
  - backend
  - enum
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Create 6-State Work Order Status Enum

## Context

Work order status enum with transition validation.

## Acceptance Criteria

1. Create `MaintOrderStatus` enum in `com.ruoyi.asset.domain.enums`:
   ```
   WAIT_ASSIGN("待派单")     - Initial state after creation
   WAIT_ACCEPT("待接单")     - After assignment
   PROCESSING("处理中")      - After handler accepts
   WAIT_CONFIRM("待验收")    - After handler completes
   CLOSED("已关闭")          - After verification passed
   CANCELED("已取消")        - Cancelled at any stage
   ```

2. Implement valid transition rules:
   - WAIT_ASSIGN → WAIT_ACCEPT (assign)
   - WAIT_ASSIGN → CANCELED (cancel)
   - WAIT_ACCEPT → PROCESSING (accept)
   - WAIT_ACCEPT → CANCELED (cancel)
   - PROCESSING → WAIT_CONFIRM (complete)
   - PROCESSING → CANCELED (cancel)
   - WAIT_CONFIRM → CLOSED (confirm pass)
   - WAIT_CONFIRM → PROCESSING (confirm reject - back to processing)
   - WAIT_CONFIRM → CANCELED (cancel)

3. Create `MaintOrderStatusMachine` class:
   - canTransition(from, to) - validate transition
   - getNextAllowedStatuses(current) - for UI action panel
   - isTerminal(status) - check if CLOSED or CANCELED

4. Add unit tests for all transition rules

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (6-state flow)
- PRD: docs/PRD.md Section 6.1.2 (状态流转)
