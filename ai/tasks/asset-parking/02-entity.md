---
id: asset-parking.entity
module: asset-parking
priority: 302
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.database-schema
  - core.asset-entity
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-parking/**/*.test.*"
---
# Create Parking Asset Entity and Mapper

## Context

Java entity class for parking extension table. Linked to base Asset entity.

## Acceptance Criteria

1. Create `AssetParking` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_parking
   - Proper annotations

2. Create `AssetParkingMapper` interface:
   - selectByAssetId(Long assetId)
   - insert, update, deleteByAssetId

3. Create mapper XML file:
   - Location: `resources/mapper/asset/AssetParkingMapper.xml`

4. Create DTO/VO classes:
   - `AssetParkingVO`: Combined view
   - `AssetParkingCreateDTO`: Create request
   - `AssetParkingUpdateDTO`: Update request

5. Entity compiles without errors

## Technical Notes

- Similar structure to AssetHouse entity
- Reference: ai/tasks/asset-house/02-entity.md
