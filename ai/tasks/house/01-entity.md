---
id: house.entity
module: house
priority: 10
status: failing
version: 13
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
    pattern: tests/house/**/*.test.*
---
# Create House Asset Entity

## Context

House assets extend the base asset with property-specific fields like area, house type, and rental information.

## Acceptance Criteria

1. Create `AssetHouse` entity extending or referencing `Asset` base
2. Map all extension fields from `t_asset_house`:
   - buildingArea (建筑面积)
   - innerArea (套内面积)
   - houseType (户型: 一居/两居等)
   - houseUsage (房屋用途: 住宅/商用/公寓)
   - currentUsage (当前用途: 自用/出租/空置)
   - currentUser (当前使用人/承租人)
   - contractNo (合同编号)
   - rentUnitPrice (租金单价)
   - rentTotal (租金总额)
3. Create VO classes for list and detail views
4. Create DTO classes for create and update operations
5. Add validation annotations for required fields

## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_house)
- Pattern: One-to-one extension table relationship
- Location: `com.ruoyi.asset.domain.entity.AssetHouse`
