---
id: dashboard.statistics-service
module: dashboard
priority: 901
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - core.base-service
  - asset-house.service
  - asset-parking.service
  - maintenance.order-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Implement Dashboard Statistics Service

## Context

Service for calculating and aggregating dashboard statistics.

## Acceptance Criteria

1. Create `IDashboardService` interface:
2. Create `DashboardServiceImpl`:
3. Create `DashboardStats` VO:
4. Create mapper methods for aggregation:
5. Performance requirement: All stats < 2s with proper indexes
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Dashboard refresh: Manual)
- Use single efficient query where possible
