---
id: asset-office.database-schema
module: asset-office
priority: 601
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
    pattern: "tests/asset-office/**/*.test.*"
---
# Create Office Asset Extension Table

## Context

Extension table for office-specific fields (furniture, IT equipment). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_office` table with office-specific fields:
   - asset_id (bigint, PK, FK to t_asset.id)
   - office_type (varchar(20)) - 资产类型 (furniture, it_equipment, other)
   - serial_number (varchar(50)) - 序列号 (for IT equipment)
   - configuration (varchar(500)) - 配置信息 (for IT equipment specs)
   - use_department (varchar(100)) - 使用部门
   - user_name (varchar(50)) - 使用人

2. Add foreign key constraint to t_asset.id

3. SQL migration file created at `sql/asset_office.sql`

4. Add dictionary entries for office_type:
   - furniture(家具) - 办公桌椅、文件柜等
   - it_equipment(IT设备) - 电脑、打印机、投影仪等
   - other(其他) - 其他办公用品

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Office Asset Module)
- PRD: docs/PRD.md Section 6.6 (办公资产模块)
