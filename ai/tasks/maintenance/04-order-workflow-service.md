---
id: maintenance.order-workflow-service
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-mapper, core.state-machine]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order Workflow Service

## Context

The workflow service handles the complete lifecycle of maintenance orders with state transitions and asset status integration.

## Acceptance Criteria

1. Create `IMaintenanceOrderService` interface with workflow methods:
   - `Long createOrder(MaintenanceOrderCreateDTO dto)` - create and optionally update asset status
   - `void assignOrder(Long orderId, Long handlerId)` - dispatch to technician
   - `void acceptOrder(Long orderId)` - technician accepts
   - `void startProcess(Long orderId)` - begin work
   - `void completeOrder(Long orderId, String result, List<String> attachments)` - submit completion
   - `void confirmOrder(Long orderId, boolean accepted, String reason)` - verify result
   - `void rejectOrder(Long orderId, String reason)` - reject and return to processing
   - `void cancelOrder(Long orderId, String reason)` - cancel order

2. Create `MaintenanceOrderServiceImpl`:

3. Implement state transition validation:
   - WAIT_ASSIGN -> WAIT_ACCEPT (via assign)
   - WAIT_ACCEPT -> PROCESSING (via accept)
   - PROCESSING -> WAIT_CONFIRM (via complete)
   - WAIT_CONFIRM -> CLOSED (via confirm with accept=true)
   - WAIT_CONFIRM -> PROCESSING (via reject)
   - Any non-closed -> CANCELED (via cancel)

4. Implement asset status integration:
   - On create: if config enabled, save originAssetStatus, update to FAULT/MAINTAINING
   - On close: restore to originAssetStatus or configured target status

5. Create log entry for each state transition

6. Use `@Transactional` for atomicity

## Technical Notes

- Reference: TECH.md section 5.3
- Critical business logic - needs thorough testing
