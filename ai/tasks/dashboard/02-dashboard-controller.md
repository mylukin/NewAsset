---
id: dashboard.controller
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [dashboard.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/dashboard/**/*.test.*"
---
# Create Dashboard Controller

## Context

API endpoints for dashboard data based on user role.

## Acceptance Criteria

1. Create `DashboardController`:
   - Base path: `/asset/dashboard`

2. Implement endpoints:
   - `GET /asset/dashboard/management` - management/ops dashboard data
   - `GET /asset/dashboard/asset-manager` - asset manager dashboard data
   - `GET /asset/dashboard/ops` - operations staff dashboard data

3. Role-based access:
   - Management endpoint: requires `dashboard:management:view`
   - Asset manager endpoint: requires `dashboard:assetmanager:view`
   - Ops endpoint: requires `dashboard:ops:view`

4. Support project filter parameter

## Technical Notes

- Reference: PRD section 7.1
