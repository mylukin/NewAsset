---
id: parking.entity
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/parking/**/*.test.*"
---
# Create Parking Asset Entity

## Context

Parking assets represent parking spaces with rental and usage information.

## Acceptance Criteria

1. Create `ParkingAsset` entity in `com.ruoyi.asset.domain.entity`:
   - Map to `t_asset_parking` table (asset_id as PK/FK)

2. Include parking-specific fields:
   - `parkingNo` (车位编号) - String
   - `parkingZone` (车库区域) - String (地上/地下/编号)
   - `parkingType` (车位类型) - String (dict: parking_type, 固定/临停)
   - `area` (车位面积) - BigDecimal (optional)
   - `currentUser` (当前使用人) - String
   - `plateNo` (车牌号) - String
   - `rentPrice` (包月金额/租金) - BigDecimal
   - `contractNo` (合同编号) - String

3. Create `ParkingAssetVO`, `ParkingAssetCreateDTO`, `ParkingAssetUpdateDTO`

## Technical Notes

- Reference: TECH.md section 4.1.2 (t_asset_parking)
