---
id: dashboard.service
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn:
  - house.statistics
  - parking.statistics
  - maintenance.order-crud-service
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/dashboard/**/*.test.*
---
# Create Dashboard Service

## Acceptance Criteria

1. Create `IDashboardService` interface:
2. Create `DashboardMetrics` VO for management view:
3. Create `AssetManagerMetrics` VO:
4. Create `OpsDashboardMetrics` VO:
5. Implement caching (5-minute TTL) for expensive aggregations
