---
id: house.frontend-api
module: house
priority: 15
status: failing
version: 1
origin: manual
dependsOn:
  - house.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Frontend API Module

## Context

Axios-based API module for frontend to communicate with house asset backend endpoints.

## Acceptance Criteria

1. Create `/src/api/asset/house.js`
2. Implement API functions:
   - `listHouse(query)` - GET /asset/house/list
   - `getHouse(id)` - GET /asset/house/{id}
   - `addHouse(data)` - POST /asset/house
   - `updateHouse(data)` - PUT /asset/house
   - `delHouse(ids)` - DELETE /asset/house/{ids}
   - `exportHouse(query)` - POST /asset/house/export
   - `copyHouse(id)` - POST /asset/house/{id}/copy
   - `getHouseStatistics(projectId)` - GET /asset/house/statistics
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- Pattern: RuoYi API module pattern
- File: `ruoyi-ui/src/api/asset/house.js`
