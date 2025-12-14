---
id: parking.mapper
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [parking.entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/parking/**/*.test.*"
---
# Create Parking Asset Mapper

## Context

Database access for parking asset CRUD operations.

## Acceptance Criteria

1. Create `ParkingAssetMapper` interface

2. Create `ParkingAssetMapper.xml`

3. Implement query methods:
   - `selectParkingAssetList(ParkingAssetQueryDTO query)` - filters: project, parkingZone, parkingNo, parkingType, status
   - `selectParkingAssetById(Long assetId)`
   - `selectParkingAssetByCode(String assetCode)`

4. Implement write methods:
   - `insertParkingAsset(ParkingAsset parking)`
   - `updateParkingAsset(ParkingAsset parking)`
   - `deleteParkingAssetByAssetId(Long assetId)`

5. Support joined queries with base asset table

## Technical Notes

- Similar pattern to other asset mappers
