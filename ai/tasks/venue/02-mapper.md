---
id: venue.mapper
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn: [venue.entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/venue/**/*.test.*"
---
# Create Venue Asset Mapper

## Context

Database access for venue asset CRUD operations.

## Acceptance Criteria

1. Create `VenueAssetMapper` interface

2. Create `VenueAssetMapper.xml`

3. Implement query methods:
   - `selectVenueAssetList(VenueAssetQueryDTO query)` - filters: project, venueType, currentVenueStatus
   - `selectVenueAssetById(Long assetId)`
   - `selectVenueAssetByCode(String assetCode)`

4. Implement write methods:
   - `insertVenueAsset(VenueAsset venue)`
   - `updateVenueAsset(VenueAsset venue)`
   - `deleteVenueAssetByAssetId(Long assetId)`

5. Support joined queries with base asset table

## Technical Notes

- Similar pattern to other asset mappers
