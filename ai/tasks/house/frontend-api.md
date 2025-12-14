---
id: house.frontend-api
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn:
  - house.controller
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
# Create House Asset Frontend API Module

## Acceptance Criteria

1. Create `/src/api/asset/house.js`:
2. Implement API functions:
3. Import RuoYi's request utility
