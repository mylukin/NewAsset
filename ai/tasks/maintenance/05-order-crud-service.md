---
id: maintenance.order-crud-service
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-mapper]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order CRUD Service

## Context

Basic CRUD operations for maintenance orders separate from workflow logic.

## Acceptance Criteria

1. Add to `IMaintenanceOrderService`:
   - `List<MaintenanceOrderVO> selectOrderList(MaintenanceOrderQueryDTO query)`
   - `MaintenanceOrderVO selectOrderById(Long id)`
   - `List<MaintenanceOrderVO> selectOrdersByAssetId(Long assetId)`
   - `List<MaintenanceOrderVO> selectMyPendingOrders()`
   - `int updateOrderBasicInfo(MaintenanceOrderUpdateDTO dto)` - update non-workflow fields

2. Implement order number generation:
   - Format: `WO{yyyyMMdd}{4-digit sequence}`
   - Example: WO2024011500001

3. Create query DTO with filters:
   - projectId
   - assetId
   - assetType
   - status (support multiple)
   - priority
   - handlerId
   - requesterId
   - dateRange (createTime)

## Technical Notes

- Reference: PRD section 6.1.2
