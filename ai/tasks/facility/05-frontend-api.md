---
id: facility.frontend-api
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn: [facility.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Facility Asset Frontend API Module

## Context

Frontend API module for facility asset operations.

## Acceptance Criteria

1. Create `/src/api/asset/facility.js`

2. Implement API functions:
   - `listFacility(query)` - GET /asset/facility/list
   - `getFacility(id)` - GET /asset/facility/{id}
   - `addFacility(data)` - POST /asset/facility
   - `updateFacility(data)` - PUT /asset/facility
   - `delFacility(ids)` - DELETE /asset/facility/{ids}
   - `exportFacility(query)` - POST /asset/facility/export

## Technical Notes

- Reference: TECH.md section 7.3
- Same pattern as house.js
