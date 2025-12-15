---
id: asset-house.database-schema
module: asset-house
priority: 201
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
    pattern: "tests/asset-house/**/*.test.*"
---
# Create House Asset Extension Table

## Context

Extension table for house-specific fields. 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_house` table with house-specific fields:
   - asset_id (bigint, PK, FK to t_asset.id)
   - building_area (decimal(10,2)) - 建筑面积
   - inner_area (decimal(10,2)) - 套内面积
   - house_type (varchar(20)) - 户型 (one_room, two_room, three_room, etc.)
   - house_usage (varchar(20)) - 房屋用途 (residential, commercial, apartment)
   - current_usage (varchar(20)) - 当前用途 (self_use, rented, idle)
   - current_user (varchar(50)) - 当前使用人/承租人
   - contract_no (varchar(50)) - 合同编号
   - rent_unit_price (decimal(10,2)) - 租金单价
   - rent_total (decimal(12,2)) - 租金总额

2. Add foreign key constraint to t_asset.id

3. SQL migration file created at `sql/asset_house.sql`

4. Add dictionary entries for house_type, house_usage, current_usage:
   - house_type: one_room(一居), two_room(两居), three_room(三居), four_room(四居), other(其他)
   - house_usage: residential(住宅), commercial(商用), apartment(公寓)
   - current_usage: self_use(自住), rented(出租), idle(空置)

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (House Asset Module)
- PRD: docs/PRD.md Section 6.2
