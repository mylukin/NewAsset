---
id: dashboard.BREAKDOWN
module: dashboard
priority: 9
status: passing
version: 8
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
  - asset-house.BREAKDOWN
  - asset-parking.BREAKDOWN
  - maintenance.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T11:35:40.174Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 7/7 criteria satisfied
---
# Dashboard Module Breakdown

## Module Purpose

Implements Dashboard screen with aggregated statistics:
- KPI card calculations (vacancy rate, work order counts)
- Manual refresh only
- Role-based dashboard variants (3 layouts)

## Scope

- Dashboard statistics service with aggregation queries
- KPI calculations: total assets, vacancy rates, work order counts
- Role-based content:
  - Management/Operations: KPIs, charts, recent work orders
  - Maintenance Staff: Pending work orders queue, my stats
  - Asset Manager: Incomplete assets, recent additions, asset counts
- Manual refresh button (no auto-refresh)
- ECharts integration for status distribution and trends

## Dependencies

- core module (asset queries)
- asset-house, asset-parking modules (vacancy statistics)
- maintenance module (work order counts)

## Related Screens

- Dashboard homepage (3 role variants)
- KPI summary cards
- Status distribution pie chart
- Trend line chart
- Recent activity tables

## Related APIs

- `GET /api/asset/dashboard/stats` - All dashboard statistics
- `GET /api/asset/dashboard/recent-orders` - Recent work orders
- `GET /api/asset/dashboard/asset-distribution` - Asset type distribution
- `GET /api/asset/dashboard/trend` - Work order trends (optional)

## Test Requirements

- Integration tests: Dashboard stats accuracy
- Performance: Dashboard stats < 2s
- E2E tests: stats-display.spec.ts

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/dashboard/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core, asset, and maintenance modules are defined
4. Statistics service with aggregation queries is included
5. Role-based dashboard layout tasks are included
6. KPI cards and charts tasks are included
7. Manual refresh functionality is included
