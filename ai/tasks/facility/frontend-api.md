---
id: facility.frontend-api
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn:
  - facility.controller
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
# Create Facility Asset Frontend API Module

## Acceptance Criteria

1. Create `/src/api/asset/facility.js`
2. Implement API functions:
