---
id: core.database-schema
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [database, infrastructure]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Database Schema for Asset Management

## Context

Design and create all database tables for the asset management system, including core asset table, extension tables for each asset type, maintenance tables, and coding rule tables.

## Acceptance Criteria

1. Core tables created:
   - `t_asset` (base asset table with all common fields)
   - `t_asset_attachment` (asset attachments)
   - `t_asset_change_log` (field change audit)
2. Extension tables created:
   - `t_asset_house` (house asset extension)
   - `t_asset_parking` (parking asset extension)
   - `t_asset_facility` (facility asset extension)
   - `t_asset_venue` (venue asset extension)
   - `t_asset_office` (office asset extension)
3. Maintenance tables created:
   - `t_asset_maint_order` (maintenance work orders)
   - `t_asset_maint_log` (work order process logs)
4. Coding rule tables created:
   - `t_asset_code_rule` (coding rule definitions)
   - `t_asset_code_seq` (sequence counters)
5. All tables have proper indexes on:
   - `(project_id, asset_type, status)` on t_asset
   - `asset_code` unique index on t_asset
   - `(project_id, status)` on t_asset_maint_order
   - `(current_handler_id, status)` on t_asset_maint_order
6. SQL migration script is created and can be executed

## Technical Notes

- **Database**: SQLite as primary database, MySQL as optional alternative
  - SQLite: For development, testing, and small deployments
  - MySQL: For production and larger scale deployments
- Use database-agnostic SQL where possible
- Provide migration scripts for both SQLite and MySQL
- Use soft delete pattern (`del_flag` field)
- Include audit fields: `create_by`, `create_time`, `update_by`, `update_time`
- Decimal fields for monetary values use `decimal(18,2)`
- Use `TEXT` type for SQLite instead of `VARCHAR` for compatibility
