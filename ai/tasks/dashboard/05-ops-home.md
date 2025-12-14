---
id: dashboard.ops-home
module: dashboard
priority: 73
status: failing
version: 15
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
# Create Operations Dashboard Home Page

## Context

Dashboard for operations/maintenance staff showing their work queue and statistics.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/ops.vue`
2. Implement pending orders list:
   - Default sort by priority (urgent first)
   - Show order no, asset, priority, create time
   - Quick action buttons (Accept, Start)
3. Implement my monthly stats:
   - Completed orders count
   - In-progress orders count
   - Simple progress indicator
4. Quick link to full order list
5. Auto-refresh every 5 minutes
6. Highlight urgent orders

## Technical Notes

- Reference: PRD Section 7.1 Point 2
- Pattern: Work queue dashboard
- Components: el-table with action buttons
