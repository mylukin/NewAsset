---
id: maintenance.order-crud-service
module: maintenance
priority: 61
status: failing
version: 12
origin: manual
dependsOn:
  - maintenance.order-mapper
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order CRUD Service

## Context

Service for maintenance order list, detail, and query operations (non-workflow).

## Acceptance Criteria

1. Create `IAssetMaintOrderService` interface
2. Create `AssetMaintOrderServiceImpl` implementation
3. Implement methods:
   - `selectMaintOrderList(query)` - paginated list
   - `selectMaintOrderById(id)` - full detail with logs
   - `selectOrdersByAssetId(assetId)` - orders for an asset
   - `selectMyPendingOrders(userId)` - technician's pending work
   - `selectRecentOrders(projectId, limit)` - for dashboard
4. Apply data scope filtering
5. Include order logs in detail view
6. Support export functionality

## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetMaintOrderServiceImpl`
