---
id: dashboard.service
module: dashboard
priority: 69
status: failing
version: 8
origin: manual
dependsOn:
  - house.statistics
  - parking.statistics
  - maintenance.order-crud-service
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Create Dashboard Service

## Context

Unified service aggregating statistics from all asset modules for dashboard display.

## Acceptance Criteria

1. Create `IDashboardService` interface
2. Create `DashboardServiceImpl` implementation
3. Implement methods for management dashboard:
   - `getAssetSummary(Long projectId)` - total assets by type
   - `getHouseVacancyRate(Long projectId)` - house vacancy metrics
   - `getParkingVacancyRate(Long projectId)` - parking vacancy metrics
   - `getMaintOrderSummary(Long projectId)` - current month orders, open orders
   - `getRecentMaintOrders(Long projectId, int limit)` - recent orders list
4. Implement methods for ops dashboard:
   - `getMyPendingOrders(Long userId)` - pending work queue
   - `getMyMonthlyStats(Long userId)` - completed/in-progress counts
5. Implement methods for asset manager dashboard:
   - `getIncompleteAssets(Long projectId)` - assets missing key fields
   - `getRecentAssets(Long projectId, int limit)` - recently added
   - `getAssetCountByType(Long projectId)` - pie chart data
6. Apply data scope filtering

## Technical Notes

- Reference: PRD Section 7
- Pattern: Aggregation service calling other services
- Location: `com.ruoyi.asset.service.impl.DashboardServiceImpl`
