---
id: maintenance.frontend-api
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-controller
supersedes: []
tags:
  - frontend
  - api
  - P0
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create Maintenance Order Frontend API Module

## Acceptance Criteria

1. Create `/src/api/asset/maintOrder.js`
2. Implement CRUD API functions:
3. Implement workflow API functions:
