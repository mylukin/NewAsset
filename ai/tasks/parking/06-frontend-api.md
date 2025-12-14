---
id: parking.frontend-api
module: parking
priority: 9
status: failing
version: 1
origin: manual
dependsOn: [parking.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Parking Asset Frontend API Module

## Context

Frontend API module for parking asset operations.

## Acceptance Criteria

1. Create `/src/api/asset/parking.js`

2. Implement API functions:
   - `listParking(query)`
   - `getParking(id)`
   - `addParking(data)`
   - `updateParking(data)`
   - `delParking(ids)`
   - `exportParking(query)`
   - `getParkingStatistics(projectId, zone)`
   - `getParkingStatisticsByZone(projectId)`

## Technical Notes

- Reference: TECH.md section 7.3
