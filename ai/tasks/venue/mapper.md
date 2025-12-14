---
id: venue.mapper
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn:
  - venue.entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Mapper

## Acceptance Criteria

1. Create `VenueAssetMapper` interface
2. Create `VenueAssetMapper.xml`
3. Implement query methods:
4. Implement write methods:
5. Support joined queries with base asset table
