---
id: dashboard.controller
module: dashboard
priority: 902
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - dashboard.statistics-service
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
---
# Implement Dashboard REST API

## Context

REST controller for dashboard statistics.

## Acceptance Criteria

1. Create `DashboardController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add permission annotations:
4. Use @DataScope for project-level filtering
5. Cache headers for client-side caching (optional)
## Technical Notes

- Reference: RuoYi standard controller patterns
- No @Log needed for read-only endpoints
