---
id: office.frontend-api
module: office
priority: 11
status: failing
version: 1
origin: manual
dependsOn: [office.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Office Asset Frontend API Module

## Context

Frontend API module for office asset operations.

## Acceptance Criteria

1. Create `/src/api/asset/office.js`

2. Implement API functions:
   - `listOffice(query)`
   - `getOffice(id)`
   - `addOffice(data)`
   - `updateOffice(data)`
   - `delOffice(ids)`
   - `exportOffice(query)`
   - `getOfficeConditionStats(projectId, deptId)` - P1

## Technical Notes

- Reference: TECH.md section 7.3
