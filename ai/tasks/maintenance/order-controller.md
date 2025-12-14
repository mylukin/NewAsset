---
id: maintenance.order-controller
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-workflow-service
  - maintenance.order-crud-service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Controller

## Acceptance Criteria

1. Create `MaintenanceOrderController`:
2. Implement CRUD endpoints:
3. Implement workflow endpoints:
4. Add permission annotations:
5. Add `@Log` annotations for all operations
