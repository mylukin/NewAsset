---
id: maintenance.order-mapper
module: maintenance
priority: 59
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-entity
  - maintenance.order-log-entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Mapper

## Context

MyBatis mappers for maintenance order and log CRUD operations.

## Acceptance Criteria

1. Create `AssetMaintOrderMapper` interface and XML
2. Implement order methods:
   - selectMaintOrderList (with asset and project joins)
   - selectMaintOrderById (with full details)
   - insertMaintOrder, updateMaintOrder
   - deleteMaintOrderByIds
3. Create `AssetMaintLogMapper` interface and XML
4. Implement log methods:
   - selectLogsByOrderId (for timeline display)
   - insertMaintLog
5. Support filters: projectId, assetType, status, priority, handlerId, requesterId, dateRange
6. Include data scope filtering
7. Join with asset and user tables for display names

## Technical Notes

- Reference: TECH.md Section 4.2
- Pattern: MyBatis XML mapper with joins
- Index usage: (project_id, status), (current_handler_id, status)
