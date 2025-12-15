---
id: maintenance.database-schema
module: maintenance
priority: 701
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
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Work Order Database Schema

## Context

Database tables for work order management and status log tracking.

## Acceptance Criteria

1. Create `t_asset_maint_order` table:
   - id (bigint, PK, auto-increment)
   - order_no (varchar(30), unique) - WO-20240115-001 format
   - asset_id (bigint, FK to t_asset.id)
   - project_id (bigint) - project scope
   - fault_type (varchar(30)) - 故障类型 dictionary
   - fault_desc (text) - 故障描述
   - priority (varchar(10)) - normal, important, urgent
   - status (varchar(20)) - 6-state machine
   - requester_id (bigint) - 发起人 (user id)
   - handler_id (bigint) - 处理人 (user id)
   - assigned_at (datetime) - 派单时间
   - accepted_at (datetime) - 接单时间
   - completed_at (datetime) - 完成时间
   - confirmed_at (datetime) - 验收时间
   - due_date (date) - 要求完成时间
   - result_desc (text) - 处理结果描述
   - material_cost (decimal(10,2)) - 耗材费用
   - labor_cost (decimal(10,2)) - 人工费用
   - link_asset_status (tinyint(1)) - 是否联动资产状态
   - create_by, create_time, update_by, update_time, del_flag (standard audit fields)

2. Create `t_asset_maint_log` table:
   - id (bigint, PK)
   - order_id (bigint, FK to t_asset_maint_order.id)
   - from_status (varchar(20))
   - to_status (varchar(20))
   - operator_id (bigint)
   - operator_name (varchar(50))
   - operation_desc (varchar(500))
   - create_time (datetime)

3. Create `t_asset_maint_order_seq` table (for WO number generation):
   - date_key (char(8), PK) - YYYYMMDD
   - current_seq (int)
   - version (int) - optimistic locking

4. Add indexes for common queries:
   - idx_order_asset_id on (asset_id)
   - idx_order_status on (status)
   - idx_order_handler on (handler_id)
   - idx_log_order_id on (order_id)

5. SQL migration file created at `sql/asset_maintenance.sql`

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Work Order format: WO-20240115-001)
- PRD: docs/PRD.md Section 6.1 (资产维护模块)
