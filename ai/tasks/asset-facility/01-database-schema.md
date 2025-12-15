---
id: asset-facility.database-schema
module: asset-facility
priority: 401
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
    pattern: "tests/asset-facility/**/*.test.*"
---
# Create Facility Asset Extension Table

## Context

Extension table for facility-specific fields (residential equipment/facilities). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_facility` table with facility-specific fields:
   - asset_id (bigint, PK, FK to t_asset.id)
   - facility_type (varchar(20)) - 配套类型 (elevator, access_control, lighting, fitness, etc.)
   - device_code (varchar(50)) - 设备编号 (manufacturer code)
   - install_location (varchar(200)) - 安装位置描述
   - maintenance_org (varchar(100)) - 维保单位
   - maintenance_phone (varchar(20)) - 维保电话
   - warranty_expire_date (date) - 保修到期日

2. Add foreign key constraint to t_asset.id

3. SQL migration file created at `sql/asset_facility.sql`

4. Add dictionary entries for facility_type:
   - elevator(电梯)
   - access_control(门禁)
   - lighting(公共照明)
   - fitness(健身设施)
   - fire_equipment(消防设备)
   - surveillance(监控设备)
   - hvac(暖通设备)
   - other(其他)

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Facility Asset Module)
- PRD: docs/PRD.md Section 6.3 (住宅配套资产模块)
