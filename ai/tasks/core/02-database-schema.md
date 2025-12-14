---
id: core.database-schema
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Create Database Schema for All Asset Tables

## Context

The system requires a comprehensive database schema including the base asset table, extension tables for each asset type, attachment table, maintenance tables, and code generation tables.

## Acceptance Criteria

1. Create `t_asset` base table with all common fields (id, asset_code, asset_name, asset_type, project_id, status, etc.)
2. Create extension tables: `t_asset_house`, `t_asset_parking`, `t_asset_facility`, `t_asset_venue`, `t_asset_office`
3. Create `t_asset_attachment` for file attachments
4. Create maintenance tables: `t_asset_maint_order`, `t_asset_maint_log`
5. Create code generation tables: `t_asset_code_rule`, `t_asset_code_seq`
6. Create P1 table: `t_asset_maint_plan` for maintenance scheduling
7. Add appropriate indexes: `(project_id, asset_type, status)`, `asset_code` unique
8. Add foreign key constraints where applicable
9. Include audit fields: `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`

## Technical Notes

- Reference: TECH.md Section 4 (Database Design)
- Use MySQL-compatible DDL syntax
- File: `sql/asset_schema.sql`
