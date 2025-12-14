---
id: facility.frontend-api
module: facility
priority: 24
status: failing
version: 16
origin: manual
dependsOn:
  - facility.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Frontend API Module

## Context

Axios API module for facility asset frontend communication.

## Acceptance Criteria

1. Create `/src/api/asset/facility.js`
2. Implement API functions:
   - listFacility, getFacility, addFacility, updateFacility, delFacility, exportFacility
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- Pattern: RuoYi API module
- File: `ruoyi-ui/src/api/asset/facility.js`
