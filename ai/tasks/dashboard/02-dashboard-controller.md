---
id: dashboard.controller
module: dashboard
priority: 70
status: failing
version: 15
origin: manual
dependsOn:
  - dashboard.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Create Dashboard Controller

## Context

RESTful API endpoints for dashboard data retrieval.

## Acceptance Criteria

1. Create `DashboardController`
2. Implement endpoints:
   - GET /asset/dashboard/summary - asset counts summary
   - GET /asset/dashboard/vacancy - vacancy rates (house + parking)
   - GET /asset/dashboard/maint-summary - maintenance order summary
   - GET /asset/dashboard/recent-orders - recent maintenance orders
   - GET /asset/dashboard/my-pending - ops pending queue
   - GET /asset/dashboard/my-stats - ops monthly stats
   - GET /asset/dashboard/incomplete-assets - assets needing attention
   - GET /asset/dashboard/recent-assets - recently added assets
   - GET /asset/dashboard/asset-distribution - asset count by type
3. All endpoints support projectId filter parameter
4. Add appropriate permission checks
5. Return optimized response for dashboard widgets

## Technical Notes

- Reference: PRD Section 7.1
- Pattern: RuoYi BaseController
- Permissions: Varies by role-based dashboard
