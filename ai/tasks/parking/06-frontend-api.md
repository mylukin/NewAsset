---
id: parking.frontend-api
module: parking
priority: 34
status: failing
version: 18
origin: manual
dependsOn:
  - parking.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Frontend API Module

## Context

Axios API module for parking asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/parking.js`
2. Implement API functions:
   - listParking, getParking, addParking, updateParking
   - delParking, exportParking, getParkingStatistics
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/parking.js`
