---
id: maintenance.order-workflow
module: maintenance
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity, maintenance.order-log, core.state-machine]
supersedes: []
tags: [domain, business-logic, critical]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Maintenance Order Workflow

## Context

Work orders follow a defined workflow from creation to closure. Status transitions are validated and can trigger asset status changes.

## Acceptance Criteria

1. `MaintOrderService` workflow methods:
   - `createOrder(dto)` - create and set WAIT_ASSIGN
   - `assignOrder(orderId, handlerId)` - WAIT_ASSIGN -> WAIT_ACCEPT
   - `acceptOrder(orderId)` - WAIT_ACCEPT -> PROCESSING
   - `completeOrder(orderId, result)` - PROCESSING -> WAIT_CONFIRM
   - `confirmOrder(orderId, passed)` - WAIT_CONFIRM -> CLOSED/PROCESSING
   - `cancelOrder(orderId, reason)` - any -> CANCELED
2. Status transition validation:
   - Only valid transitions allowed
   - Check current status before each operation
   - Return clear error for invalid transitions
3. Log creation:
   - Each transition creates a MaintOrderLog entry
   - Include operator, time, and optional notes
4. Asset status integration:
   - On create: optionally set asset to FAULT/MAINTAINING
   - On close: restore asset to originAssetStatus
   - Configurable via system settings
5. Notification hooks (placeholder):
   - On assign: notify handler
   - On complete: notify requester
6. Unit tests cover:
   - All valid transitions
   - Invalid transition rejection
   - Asset status changes
   - Log creation

## Technical Notes

- Use @Transactional for atomic operations
- Consider Spring Events for async notifications
- Status transitions configurable via dictionary
