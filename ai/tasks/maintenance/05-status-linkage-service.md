---
id: maintenance.status-linkage-service
module: maintenance
priority: 705
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.status-enum
  - core.status-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Status Linkage Service

## Context

Service to optionally link work order status changes with asset status changes.

## Acceptance Criteria

1. Create `StatusLinkageService`:
   - onWorkOrderCreated(orderId, linkAssetStatus)
     - If linkAssetStatus=true, change asset to MAINTAINING
   - onWorkOrderCompleted(orderId)
     - If linked, restore asset to previous status or prompt user
   - onWorkOrderCanceled(orderId)
     - If linked and asset was changed, restore original status

2. Implement status restoration logic:
   - Store original asset status when work order created (if linked)
   - On completion/cancel, restore to original status
   - If original status was FAULT, restore to FAULT (not auto-fix)

3. Add optional checkbox behavior:
   - Default: checked (link status)
   - User can uncheck to create WO without changing asset status
   - Store linkage decision in MaintOrder.linkAssetStatus

4. Log all status linkage operations:
   - Asset ID, from status, to status, reason (WO created/closed)
   - For audit trail

5. Handle edge cases:
   - Asset already in MAINTAINING - no change needed
   - Asset deleted before WO closed - skip restoration
   - Multiple open WOs for same asset - don't restore until all closed

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Optional checkbox, default checked)
- PRD: docs/PRD.md Section 6.1.2.3 (资产状态联动)
