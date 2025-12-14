---
id: maintenance.order-workflow-service
module: maintenance
priority: 60
status: failing
version: 12
origin: manual
dependsOn:
  - maintenance.order-mapper
  - core.state-machine
supersedes: []
tags:
  - service
  - workflow
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Workflow Service

## Context

Service handling the state machine logic for maintenance order workflow transitions with asset status integration.

## Acceptance Criteria

1. Create `MaintOrderWorkflowService` in `com.ruoyi.asset.service.rule`
2. Implement workflow methods:
   - `createOrder(CreateOrderDTO dto)` - create and optionally set asset to FAULT/MAINTAINING
   - `assignOrder(Long orderId, Long handlerId)` - assign to technician
   - `acceptOrder(Long orderId)` - technician accepts
   - `startHandle(Long orderId)` - begin processing
   - `completeOrder(Long orderId, String result, String attachments)` - submit completion
   - `confirmOrder(Long orderId, boolean approved, String comment)` - verify/reject
   - `cancelOrder(Long orderId, String reason)` - cancel order
3. Each method must:
   - Validate current status allows transition
   - Update order status
   - Insert log record
   - Trigger asset status change if configured
4. Define valid transitions:
   - WAIT_ASSIGN → WAIT_ACCEPT (assign) / CANCELED (cancel)
   - WAIT_ACCEPT → PROCESSING (accept) / CANCELED (cancel)
   - PROCESSING → WAIT_CONFIRM (complete)
   - WAIT_CONFIRM → CLOSED (confirm approved) / PROCESSING (reject)
5. Integrate with AssetStatusService for status restoration on close

## Technical Notes

- Reference: TECH.md Section 5.3
- Pattern: Workflow state machine
- Transaction: All transitions in single transaction
