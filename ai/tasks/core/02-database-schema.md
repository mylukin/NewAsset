---
id: core.database-schema
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [database, sql]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Database Schema for All Asset Tables

## Context

The asset management system requires a comprehensive database schema including base asset table, extension tables for each asset type, attachment table, and maintenance-related tables.

## Acceptance Criteria

1. Create `t_asset` base table with all common fields:
   - id, asset_code (unique), asset_name, asset_type
   - project_id, building, floor, room_no, location_desc
   - ownership_type, owner_org, use_dept_id, duty_user_id
   - status (unified state machine)
   - purchase_date, start_use_date, original_value
   - depreciation_method, depreciation_amount, net_value
   - brand, model, supplier, warranty_expire_date
   - remark, audit fields (create_by, create_time, update_by, update_time, del_flag)

2. Create extension tables:
   - `t_asset_house` (房源扩展)
   - `t_asset_parking` (车位扩展)
   - `t_asset_facility` (配套扩展)
   - `t_asset_venue` (场馆扩展)
   - `t_asset_office` (办公扩展)

3. Create `t_asset_attachment` for file attachments

4. Create maintenance tables:
   - `t_asset_maint_order` (维护工单)
   - `t_asset_maint_log` (工单流转日志)

5. Create code generation tables:
   - `t_asset_code_rule` (编码规则)
   - `t_asset_code_seq` (编码序列)

6. Create appropriate indexes for query performance:
   - `(project_id, asset_type, status)` on t_asset
   - `asset_code` unique index on t_asset
   - `(project_id, status)` on t_asset_maint_order

## Technical Notes

- Reference: TECH.md section 4 (Database Design)
- All tables use soft delete (del_flag)
