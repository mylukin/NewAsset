---
id: venue.mapper
module: venue
priority: 40
status: failing
version: 10
origin: manual
dependsOn:
  - venue.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Mapper

## Context

MyBatis mapper for venue asset CRUD operations.

## Acceptance Criteria

1. Create `AssetVenueMapper` interface
2. Create XML mapper file
3. Implement methods:
   - selectAssetVenueList, selectAssetVenueById
   - insertAssetVenue, updateAssetVenue, deleteAssetVenueByIds
4. Support filters: projectId, venueType, currentVenueStatus, status
5. Include data scope filtering
6. Join with base asset table

## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetVenueMapper.xml`
