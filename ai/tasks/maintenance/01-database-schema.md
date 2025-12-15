---
id: maintenance.database-schema
module: maintenance
priority: 701
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - database
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Work Order Database Schema

## Context

Database tables for work order management and status log tracking.

## Acceptance Criteria

1. Create `t_asset_maint_order` table:
2. Create `t_asset_maint_log` table:
3. Create `t_asset_maint_order_seq` table (for WO number generation):
4. Add indexes for common queries:
5. SQL migration file created at `sql/asset_maintenance.sql`
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Work Order format: WO-20240115-001)
- PRD: docs/PRD.md Section 6.1 (资产维护模块)
