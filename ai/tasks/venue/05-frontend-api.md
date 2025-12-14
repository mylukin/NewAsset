---
id: venue.frontend-api
module: venue
priority: 43
status: failing
version: 12
origin: manual
dependsOn:
  - venue.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Frontend API Module

## Context

Axios API module for venue asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/venue.js`
2. Implement API functions:
   - listVenue, getVenue, addVenue, updateVenue, delVenue, exportVenue
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/venue.js`
