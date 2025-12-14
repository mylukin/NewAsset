---
id: facility.mapper
module: facility
priority: 21
status: failing
version: 16
origin: manual
dependsOn:
  - facility.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Mapper

## Context

MyBatis mapper for facility asset CRUD operations with base asset table join.

## Acceptance Criteria

1. Create `AssetFacilityMapper` interface
2. Create corresponding XML mapper file
3. Implement methods:
   - `selectAssetFacilityList(AssetFacility query)` - paginated list
   - `selectAssetFacilityById(Long id)` - detail with base asset
   - `insertAssetFacility(AssetFacility facility)` - insert both tables
   - `updateAssetFacility(AssetFacility facility)` - update both tables
   - `deleteAssetFacilityByIds(Long[] ids)` - soft delete
4. Support filters: projectId, building, floor, facilityType, status
5. Include data scope filtering

## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper with join
- Location: `resources/mapper/asset/AssetFacilityMapper.xml`
