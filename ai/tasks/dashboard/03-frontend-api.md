---
id: dashboard.frontend-api
module: dashboard
priority: 71
status: failing
version: 15
origin: manual
dependsOn:
  - dashboard.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Create Dashboard Frontend API Module

## Context

Axios API module for dashboard data retrieval.

## Acceptance Criteria

1. Create `/src/api/asset/dashboard.js`
2. Implement API functions:
   - getAssetSummary(projectId)
   - getVacancyRates(projectId)
   - getMaintSummary(projectId)
   - getRecentOrders(projectId, limit)
   - getMyPendingOrders()
   - getMyStats()
   - getIncompleteAssets(projectId)
   - getRecentAssets(projectId, limit)
   - getAssetDistribution(projectId)
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/dashboard.js`
