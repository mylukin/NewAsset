---
id: parking.frontend-api
module: parking
priority: 9
status: failing
version: 1
origin: manual
dependsOn:
  - parking.controller
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
# Create Parking Asset Frontend API Module

## Acceptance Criteria

1. Create `/src/api/asset/parking.js`
2. Implement API functions:
