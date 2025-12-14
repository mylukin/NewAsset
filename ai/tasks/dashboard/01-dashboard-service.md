---
id: dashboard.service
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [house.statistics, parking.statistics, maintenance.order-crud-service]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/dashboard/**/*.test.*"
---
# Create Dashboard Service

## Context

Aggregate statistics from all asset modules for dashboard display.

## Acceptance Criteria

1. Create `IDashboardService` interface:
   - `DashboardMetrics getManagementMetrics(Long projectId)` - for management/ops
   - `DashboardMetrics getAssetManagerMetrics(Long projectId)` - for asset managers
   - `OpsDashboardMetrics getOpsMetrics(Long userId)` - for ops staff

2. Create `DashboardMetrics` VO for management view:
   - `totalAssetCount` - total assets across all types
   - `houseVacancyRate` - house vacancy rate
   - `parkingVacancyRate` - parking vacancy rate
   - `newOrdersThisMonth` - new maintenance orders this month
   - `openOrderCount` - unclosed orders
   - `recentOrders` - List of recent orders (top 10)
   - `assetCountByType` - Map for asset distribution

3. Create `AssetManagerMetrics` VO:
   - `incompleteAssets` - assets missing key fields
   - `recentAssets` - recently added assets
   - `assetCountByCategory` - pie chart data

4. Create `OpsDashboardMetrics` VO:
   - `myPendingOrders` - orders waiting for me
   - `myCompletedThisMonth` - completed count
   - `myInProgressCount` - in progress count

5. Implement caching (5-minute TTL) for expensive aggregations

## Technical Notes

- Reference: PRD section 7.1
