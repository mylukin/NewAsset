---
id: venue.service
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn:
  - venue.mapper
  - core.code-generator
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Service

## Acceptance Criteria

1. Create `IVenueAssetService` interface:
2. Create `VenueAssetServiceImpl`
3. Implement status update logic:
