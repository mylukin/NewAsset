---
id: dashboard.management-home
module: dashboard
priority: 72
status: failing
version: 8
origin: manual
dependsOn:
  - dashboard.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Create Management Dashboard Home Page

## Context

Dashboard for management and operations roles showing key metrics and recent activity.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/management.vue`
2. Implement metric cards:
   - Total asset count (with breakdown by type)
   - House vacancy rate (with trend indicator)
   - Parking vacancy rate
   - This month new orders count
   - Current open orders count
3. Implement recent orders table (Top 10)
4. Add project selector to filter all data
5. Auto-refresh on project change
6. Use ECharts for pie chart of asset distribution
7. Loading states for each widget
8. Responsive layout for 1920x1080

## Technical Notes

- Reference: PRD Section 7.1 Point 1
- Pattern: Dashboard with cards and charts
- Components: el-card, ECharts
