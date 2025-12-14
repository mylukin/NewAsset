---
id: dashboard.management-home
module: dashboard
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [house.statistics, parking.statistics, maintenance.order-list]
supersedes: []
tags: [feature, dashboard, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Management Dashboard

## Context

The management dashboard provides an overview of key metrics for managers and operations staff. It aggregates data from all asset modules.

## Acceptance Criteria

1. Backend API `GET /asset/dashboard/management`:
   - Returns DashboardVO with aggregated metrics
   - Respects data permission (by project)
   - Query param: projectId (optional filter)
2. Metrics included:
   - Total asset count (by type breakdown)
   - House vacancy rate
   - Parking vacancy rate
   - Current month new maintenance orders
   - Unclosed maintenance orders count
3. Recent orders list:
   - Top 10 recent orders
   - Shows order number, title, status, time
4. Frontend page:
   - Metric cards at top
   - Project selector dropdown
   - Card grid layout:
     - Total assets (with type breakdown pie chart)
     - House vacancy (with trend line - optional)
     - Parking vacancy
     - Maintenance orders (new this month vs open)
   - Recent orders table below
5. Click interactions:
   - Click metric card to navigate to detail module
   - Click order to go to order detail
6. Responsive layout for 1920x1080

## Technical Notes

- Aggregate queries should be optimized
- Consider caching for dashboard data
- Use ECharts for visualizations
