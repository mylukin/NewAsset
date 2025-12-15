---
id: asset-parking.database-schema
module: asset-parking
priority: 301
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - database
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-parking/**/*.test.*"
---
# Create Parking Asset Extension Table

## Context

Extension table for parking-specific fields. 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_parking` table:
   - asset_id (bigint, PK, FK to t_asset.id)
   - parking_no (varchar(20)) - 车位编号
   - parking_zone (varchar(50)) - 车库区域
   - parking_type (varchar(20)) - 车位类型 (fixed, temporary)
   - area (decimal(8,2)) - 车位面积
   - current_user (varchar(50)) - 当前使用人
   - plate_no (varchar(20)) - 车牌号
   - rent_price (decimal(10,2)) - 租金/包月金额
   - contract_no (varchar(50)) - 合同编号

2. Add foreign key constraint to t_asset.id

3. SQL migration file: `sql/asset_parking.sql`

4. Add dictionary entries:
   - parking_type: fixed(固定车位), temporary(临停车位), reserved(预留车位)
   - parking_zone: underground_1(地下1层), underground_2(地下2层), ground(地面)

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Parking Asset Module)
- PRD: docs/PRD.md Section 6.4
