---
id: dashboard.controller
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn:
  - dashboard.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/dashboard/**/*.test.*
---
# Create Dashboard Controller

## Acceptance Criteria

1. Create `DashboardController`:
2. Implement endpoints:
3. Role-based access:
4. Support project filter parameter
