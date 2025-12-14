---
id: maintenance.order-entity
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - entity
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Entity

## Acceptance Criteria

1. Create `MaintenanceOrder` entity mapping `t_asset_maint_order`:
2. Create `MaintOrderStatusEnum`:
3. Create `MaintOperationTypeEnum`:
4. Create `MaintenanceOrderVO`, DTOs
