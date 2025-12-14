---
id: dashboard.management-home
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [dashboard.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Management Dashboard Page

## Context

Dashboard for management and operations roles showing key metrics and recent activity.

## Acceptance Criteria

1. Create `/src/views/dashboard/ManagementHome.vue`

2. Implement project selector at top

3. Implement metric cards row:
   - Total Assets (with icon)
   - House Vacancy Rate (%)
   - Parking Vacancy Rate (%)
   - New Orders This Month
   - Open Orders (clickable to order list)

4. Implement recent orders table:
   - Top 10 recent maintenance orders
   - Columns: Order No, Asset, Priority, Status, Create Time
   - Click to view order detail

5. Implement asset distribution chart:
   - Pie or donut chart by asset type
   - Use ECharts or similar

6. Implement quick actions:
   - View all assets
   - View all orders

7. Style with appropriate colors:
   - High vacancy rates in warning color
   - Open orders count in alert color if high

## Technical Notes

- Reference: PRD section 7.1 point 1
