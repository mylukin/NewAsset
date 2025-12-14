---
id: facility.entity
module: facility
priority: 20
status: failing
version: 18
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
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Entity

## Context

Facility assets represent residential supporting equipment like elevators, access control, and public lighting systems.

## Acceptance Criteria

1. Create `AssetFacility` entity referencing `Asset` base
2. Map extension fields from `t_asset_facility`:
   - facilityType (配套类型: 电梯/门禁/公共照明等)
   - installPosition (安装位置描述)
   - equipmentNo (设备编号 - 原厂编号)
   - maintOrg (维保单位)
   - maintPhone (维保电话)
3. Create VO classes for list and detail views
4. Create DTO classes for create and update operations
5. Add validation annotations for required fields

## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_facility)
- Pattern: One-to-one extension table relationship
- Location: `com.ruoyi.asset.domain.entity.AssetFacility`
