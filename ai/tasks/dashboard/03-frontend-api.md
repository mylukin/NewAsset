---
id: dashboard.frontend-api
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [dashboard.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Dashboard Frontend API Module

## Context

Frontend API for dashboard data.

## Acceptance Criteria

1. Create `/src/api/asset/dashboard.js`

2. Implement API functions:
   ```javascript
   getManagementDashboard(projectId)
   getAssetManagerDashboard(projectId)
   getOpsDashboard()
   ```

## Technical Notes

- Reference: TECH.md section 7.3
