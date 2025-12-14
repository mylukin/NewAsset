---
id: maintenance.order-mapper
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-entity
  - maintenance.order-log-entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Mapper

## Acceptance Criteria

1. Create `MaintenanceOrderMapper` interface
2. Create `MaintenanceOrderMapper.xml`
3. Implement query methods:
4. Implement write methods:
5. Create `MaintenanceOrderLogMapper`:
6. Include data permission for project-based access
