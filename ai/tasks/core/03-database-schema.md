---
id: core.database-schema
module: core
priority: 3
status: failing
version: 5
origin: manual
dependsOn:
  - core.project-init
  - core.db-config
supersedes: []
tags:
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T09:39:17.468Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 22ce1fe788aeb6dc8c0ab34b40fc7ca3e6e99b0b
  summary: 15/15 criteria satisfied
---
# Create Database Schema for All Asset Tables (Dual-DB Support)

## Context

The system requires a comprehensive database schema including the base asset table, extension tables for each asset type, attachment table, maintenance tables, and code generation tables. The schema must support both SQLite (development) and MySQL (production) databases.

## Acceptance Criteria

### Schema Files
1. Create `sql/schema-mysql.sql` for production environment (MySQL)
2. Create `sql/schema-sqlite.sql` for development environment (SQLite)
3. Both schemas must create identical table structures with dialect-appropriate syntax

### Core Tables (in both schemas)
4. Create `t_asset` base table with all common fields (id, asset_code, asset_name, asset_type, project_id, status, etc.)
5. Create extension tables: `t_asset_house`, `t_asset_parking`, `t_asset_facility`, `t_asset_venue`, `t_asset_office`
6. Create `t_asset_attachment` for file attachments
7. Create maintenance tables: `t_asset_maint_order`, `t_asset_maint_log`
8. Create code generation tables: `t_asset_code_rule`, `t_asset_code_seq`
9. Create P1 table: `t_asset_maint_plan` for maintenance scheduling

### Indexes and Constraints
10. Add appropriate indexes: `(project_id, asset_type, status)`, `asset_code` unique
11. Add foreign key constraints where applicable
12. Include audit fields: `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`

### Dialect-Specific Handling
13. MySQL schema uses: `AUTO_INCREMENT`, `DATETIME`, `TINYINT(1)` for boolean, `ENGINE=InnoDB`
14. SQLite schema uses: `INTEGER PRIMARY KEY` (auto-increment), `TEXT` for datetime, `INTEGER` for boolean
15. SQLite schema includes `PRAGMA foreign_keys = ON;` at the beginning

## Technical Notes

- Reference: TECH.md Section 4 (Database Design)
- MySQL file: `sql/schema-mysql.sql`
- SQLite file: `sql/schema-sqlite.sql`
- Use comments to mark dialect-specific sections
- Test both schemas can be executed without errors

## SQL Dialect Mapping

| MySQL | SQLite | Notes |
|-------|--------|-------|
| `BIGINT AUTO_INCREMENT` | `INTEGER PRIMARY KEY` | SQLite auto-increments INTEGER PRIMARY KEY |
| `DATETIME` | `TEXT` | Store as ISO8601 string |
| `TINYINT(1)` | `INTEGER` | 0/1 for boolean |
| `VARCHAR(n)` | `TEXT` | SQLite ignores length constraints |
| `DECIMAL(m,n)` | `REAL` | SQLite uses floating point |
| `ENGINE=InnoDB` | (remove) | SQLite doesn't have storage engines |
