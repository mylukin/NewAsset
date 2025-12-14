---
id: house.entity
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/house/**/*.test.*"
---
# Create House Asset Entity

## Context

House assets represent residential properties with specific attributes like area, room type, and rental information.

## Acceptance Criteria

1. Create `HouseAsset` entity in `com.ruoyi.asset.domain.entity`:
   - Extend or compose with base `Asset` entity
   - Map to `t_asset_house` table (asset_id as PK/FK)

2. Include house-specific fields:
   - `buildingArea` (建筑面积) - BigDecimal
   - `innerArea` (套内面积) - BigDecimal
   - `houseType` (户型) - String (dict: house_type)
   - `houseUsage` (房屋用途) - String (dict: house_usage)
   - `currentUsage` (当前用途) - String (自用/出租/空置)
   - `currentUser` (当前使用人/承租人) - String
   - `contractNo` (合同编号) - String
   - `rentUnitPrice` (租金单价) - BigDecimal
   - `rentTotal` (租金总额) - BigDecimal

3. Add validation annotations:
   - `buildingArea` must be positive
   - `innerArea` must be <= `buildingArea`

4. Create `HouseAssetVO` for API responses (combined view with base asset)

5. Create `HouseAssetCreateDTO` and `HouseAssetUpdateDTO` for API requests

## Technical Notes

- Reference: TECH.md section 4.1.2
- Pattern: One-to-one relationship with t_asset
