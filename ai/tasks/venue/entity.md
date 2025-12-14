---
id: venue.entity
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - entity
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Entity

## Acceptance Criteria

1. Create `VenueAsset` entity in `com.ruoyi.asset.domain.entity`:
2. Include venue-specific fields:
3. Create `VenueStatusEnum`:
4. Create `VenueAssetVO`, `VenueAssetCreateDTO`, `VenueAssetUpdateDTO`
