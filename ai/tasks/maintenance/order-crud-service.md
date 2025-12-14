---
id: maintenance.order-crud-service
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-mapper
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
# Create Maintenance Order CRUD Service

## Acceptance Criteria

1. Add to `IMaintenanceOrderService`:
2. Implement order number generation:
3. Create query DTO with filters:
