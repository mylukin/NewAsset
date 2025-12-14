---
id: facility.mapper
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn: [facility.entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/facility/**/*.test.*"
---
# Create Facility Asset Mapper

## Context

The mapper provides database access for facility asset CRUD operations.

## Acceptance Criteria

1. Create `FacilityAssetMapper` interface

2. Create `FacilityAssetMapper.xml` with SQL mappings

3. Implement query methods:
   - `selectFacilityAssetList(FacilityAssetQueryDTO query)` - with filters: project, building, floor, facilityType, status
   - `selectFacilityAssetById(Long assetId)`
   - `selectFacilityAssetByCode(String assetCode)`

4. Implement write methods:
   - `insertFacilityAsset(FacilityAsset facility)`
   - `updateFacilityAsset(FacilityAsset facility)`
   - `deleteFacilityAssetByAssetId(Long assetId)`

5. Support joined queries with base asset table

6. Include data permission placeholder

## Technical Notes

- Reference: TECH.md section 6.4
- Similar pattern to HouseAssetMapper
