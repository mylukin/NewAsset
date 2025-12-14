---
id: parking.mapper
module: parking
priority: 30
status: failing
version: 4
origin: manual
dependsOn:
  - parking.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Mapper

## Context

MyBatis mapper for parking asset CRUD operations.

## Acceptance Criteria

1. Create `AssetParkingMapper` interface
2. Create XML mapper file
3. Implement methods:
   - selectAssetParkingList, selectAssetParkingById
   - insertAssetParking, updateAssetParking, deleteAssetParkingByIds
4. Support filters: projectId, parkingZone, parkingNo, parkingType, status
5. Include data scope filtering
6. Join with base asset table

## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetParkingMapper.xml`
