---
id: venue.controller
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn:
  - venue.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Controller

## Acceptance Criteria

1. Create `VenueAssetController`:
2. Implement endpoints:
3. Add permission annotations:
4. Add `@Log` annotations
