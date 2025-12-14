---
id: maintenance.order-mapper
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity, maintenance.order-log-entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order Mapper

## Context

Database access for maintenance order CRUD and workflow operations.

## Acceptance Criteria

1. Create `MaintenanceOrderMapper` interface

2. Create `MaintenanceOrderMapper.xml`

3. Implement query methods:
   - `selectOrderList(MaintenanceOrderQueryDTO query)` - filters: project, assetType, status, priority, handlerId
   - `selectOrderById(Long id)`
   - `selectOrderByOrderNo(String orderNo)`
   - `selectOrdersByAssetId(Long assetId)` - for asset detail page
   - `selectMyPendingOrders(Long handlerId)` - for ops dashboard
   - `countOpenOrdersByAssetId(Long assetId)` - check before asset delete

4. Implement write methods:
   - `insertOrder(MaintenanceOrder order)`
   - `updateOrder(MaintenanceOrder order)`
   - `updateOrderStatus(Long id, String status, Long handlerId)`

5. Create `MaintenanceOrderLogMapper`:
   - `insertLog(MaintenanceOrderLog log)`
   - `selectLogsByOrderId(Long orderId)` - for order timeline

6. Include data permission for project-based access

## Technical Notes

- Reference: TECH.md section 4.2
