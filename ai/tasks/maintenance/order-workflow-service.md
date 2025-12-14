---
id: maintenance.order-workflow-service
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-mapper
  - core.state-machine
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Workflow Service

## Acceptance Criteria

1. Create `IMaintenanceOrderService` interface with workflow methods:
2. Create `MaintenanceOrderServiceImpl`:
3. Implement state transition validation:
4. Implement asset status integration:
5. Create log entry for each state transition
6. Use `@Transactional` for atomicity
