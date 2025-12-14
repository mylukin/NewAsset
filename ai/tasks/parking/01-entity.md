---
id: parking.entity
module: parking
priority: 29
status: failing
version: 21
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Entity

## Context

Parking assets represent parking spaces with usage status and rental information.

## Acceptance Criteria

1. Create `AssetParking` entity referencing `Asset` base
2. Map extension fields from `t_asset_parking`:
   - parkingNo (车位编号)
   - parkingZone (车库区域: 地上/地下/编号)
   - parkingType (车位类型: 固定/临停)
   - area (车位面积)
   - currentUser (当前使用人)
   - plateNo (车牌号)
   - rentPrice (租金/包月金额)
   - contractNo (合同编号)
3. Create VO and DTO classes
4. Add validation annotations

## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_parking)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetParking`
