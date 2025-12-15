---
id: dashboard.statistics-service
module: dashboard
priority: 901
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.base-service
  - asset-house.service
  - asset-parking.service
  - maintenance.order-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/dashboard/**/*.test.*"
---
# Implement Dashboard Statistics Service

## Context

Service for calculating and aggregating dashboard statistics.

## Acceptance Criteria

1. Create `IDashboardService` interface:
   - getStats(Long projectId) - Main statistics
   - getRecentOrders(Long projectId, int limit) - Recent work orders
   - getAssetDistribution(Long projectId) - By type
   - getStatusDistribution(Long projectId, String assetType) - By status

2. Create `DashboardServiceImpl`:
   - Aggregate queries across asset tables
   - Use SQL COUNT/SUM for performance

3. Create `DashboardStats` VO:
   - totalAssets: int - Total asset count
   - assetsByType: Map (house, parking, facility, venue, office)
   - houseVacancyRate: double - % of houses in idle status
   - parkingUtilizationRate: double - % of parking in use
   - pendingWorkOrders: int - WAIT_ASSIGN + WAIT_ACCEPT
   - inProgressWorkOrders: int - PROCESSING
   - waitingConfirmWorkOrders: int - WAIT_CONFIRM
   - completedThisMonth: int - Work orders closed this month

4. Create mapper methods for aggregation:
   - countByStatus(projectId, assetType)
   - countByType(projectId)
   - sumAssets(projectId)

5. Performance requirement: All stats < 2s with proper indexes

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Dashboard refresh: Manual)
- Use single efficient query where possible
