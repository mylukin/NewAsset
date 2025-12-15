---
id: dashboard.controller
module: dashboard
priority: 902
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - dashboard.statistics-service
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/dashboard/**/*.test.*"
---
# Implement Dashboard REST API

## Context

REST controller for dashboard statistics.

## Acceptance Criteria

1. Create `DashboardController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/dashboard`

2. Implement endpoints:
   - `GET /stats` - Main dashboard statistics
     - Query params: projectId (optional)
     - Returns: DashboardStats

   - `GET /recent-orders` - Recent work orders
     - Query params: projectId, limit (default 10)
     - Returns: List of recent MaintOrderVO

   - `GET /asset-distribution` - Asset count by type
     - Query params: projectId
     - Returns: Map of type -> count

   - `GET /status-distribution` - Asset count by status
     - Query params: projectId, assetType (optional)
     - Returns: Map of status -> count for charts

   - `GET /trend` - Work order completion trend (optional P1)
     - Query params: projectId, days (default 30)
     - Returns: List of date -> count pairs

3. Add permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:dashboard:view')")
   - All dashboard endpoints use same permission

4. Use @DataScope for project-level filtering

5. Cache headers for client-side caching (optional)

## Technical Notes

- Reference: RuoYi standard controller patterns
- No @Log needed for read-only endpoints
