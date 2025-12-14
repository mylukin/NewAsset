---
id: dashboard.asset-manager-home
module: dashboard
priority: 74
status: failing
version: 3
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
# Create Asset Manager Dashboard Home Page

## Context

Dashboard for asset managers showing assets needing attention and recent additions.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/assetManager.vue`
2. Implement incomplete assets list:
   - Assets missing key fields (like duty user, value, etc.)
   - Show asset code, name, type, missing fields
   - Quick link to edit
3. Implement recent assets list:
   - Recently added assets
   - Show code, name, type, create time
4. Implement asset distribution chart:
   - Pie or bar chart by asset type
   - Interactive for drill-down
5. Add project selector
6. Loading and empty states

## Technical Notes

- Reference: PRD Section 7.1 Point 3
- Pattern: Admin dashboard
- Components: el-table, ECharts
