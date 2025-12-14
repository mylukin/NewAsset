---
id: venue.frontend-api
module: venue
priority: 15
status: failing
version: 1
origin: manual
dependsOn: [venue.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Venue Asset Frontend API Module

## Context

Frontend API module for venue asset operations.

## Acceptance Criteria

1. Create `/src/api/asset/venue.js`

2. Implement API functions:
   - `listVenue(query)`
   - `getVenue(id)`
   - `addVenue(data)`
   - `updateVenue(data)`
   - `delVenue(ids)`
   - `exportVenue(query)`
   - `updateVenueStatus(id, status)` - update availability

## Technical Notes

- Reference: TECH.md section 7.3
