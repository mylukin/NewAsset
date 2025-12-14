---
id: core.database-schema
module: core
priority: 3
status: passing
version: 10
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
  verifiedAt: '2025-12-14T11:57:54.091Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: bd85dbf715ce385a44f378c817d2cee9a8603939
  summary: 15/15 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:57:41.283Z'
  generatedBy: claude
  forVersion: 7
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/schema/SchemaValidationTest.java
    e2e: []
  unitTestCases:
    - name: should create valid MySQL schema file at sql/schema-mysql.sql
      assertions:
        - assertFileExists('sql/schema-mysql.sql')
        - assertFileNotEmpty('sql/schema-mysql.sql')
    - name: should create valid SQLite schema file at sql/schema-sqlite.sql
      assertions:
        - assertFileExists('sql/schema-sqlite.sql')
        - assertFileNotEmpty('sql/schema-sqlite.sql')
    - name: should have identical table structures in both schemas
      assertions:
        - 'assertTableExists(''t_asset'', mysqlSchema)'
        - 'assertTableExists(''t_asset'', sqliteSchema)'
        - 'assertColumnCountEqual(''t_asset'', mysqlSchema, sqliteSchema)'
    - name: should create t_asset base table with all common fields
      assertions:
        - 'assertColumnExists(''t_asset'', ''id'')'
        - 'assertColumnExists(''t_asset'', ''asset_code'')'
        - 'assertColumnExists(''t_asset'', ''asset_name'')'
        - 'assertColumnExists(''t_asset'', ''asset_type'')'
        - 'assertColumnExists(''t_asset'', ''project_id'')'
        - 'assertColumnExists(''t_asset'', ''status'')'
    - name: should create extension tables for different asset types
      assertions:
        - assertTableExists('t_asset_house')
        - assertTableExists('t_asset_parking')
        - assertTableExists('t_asset_facility')
        - assertTableExists('t_asset_venue')
        - assertTableExists('t_asset_office')
    - name: should create t_asset_attachment table for file attachments
      assertions:
        - assertTableExists('t_asset_attachment')
        - 'assertColumnExists(''t_asset_attachment'', ''asset_id'')'
        - 'assertColumnExists(''t_asset_attachment'', ''file_path'')'
    - name: should create maintenance tables
      assertions:
        - assertTableExists('t_asset_maint_order')
        - assertTableExists('t_asset_maint_log')
    - name: should create code generation tables
      assertions:
        - assertTableExists('t_asset_code_rule')
        - assertTableExists('t_asset_code_seq')
    - name: should create t_asset_maint_plan table for maintenance scheduling
      assertions:
        - assertTableExists('t_asset_maint_plan')
        - 'assertColumnExists(''t_asset_maint_plan'', ''plan_name'')'
        - 'assertColumnExists(''t_asset_maint_plan'', ''schedule_type'')'
    - name: should add appropriate indexes on t_asset table
      assertions:
        - 'assertIndexExists(''t_asset'', ''idx_asset_project_type_status'')'
        - 'assertUniqueIndexExists(''t_asset'', ''asset_code'')'
    - name: should add foreign key constraints where applicable
      assertions:
        - 'assertForeignKeyExists(''t_asset_house'', ''asset_id'', ''t_asset'', ''id'')'
        - >-
          assertForeignKeyExists('t_asset_attachment', 'asset_id', 't_asset',
          'id')
    - name: should include audit fields in all tables
      assertions:
        - 'assertColumnExists(''t_asset'', ''create_by'')'
        - 'assertColumnExists(''t_asset'', ''create_time'')'
        - 'assertColumnExists(''t_asset'', ''update_by'')'
        - 'assertColumnExists(''t_asset'', ''update_time'')'
        - 'assertColumnExists(''t_asset'', ''del_flag'')'
    - name: should use MySQL-specific syntax in MySQL schema
      assertions:
        - 'assertContains(mysqlSchema, ''AUTO_INCREMENT'')'
        - 'assertContains(mysqlSchema, ''DATETIME'')'
        - 'assertContains(mysqlSchema, ''TINYINT(1)'')'
        - 'assertContains(mysqlSchema, ''ENGINE=InnoDB'')'
    - name: should use SQLite-specific syntax in SQLite schema
      assertions:
        - 'assertContains(sqliteSchema, ''INTEGER PRIMARY KEY'')'
        - 'assertContains(sqliteSchema, ''TEXT'')'
        - 'assertNotContains(sqliteSchema, ''AUTO_INCREMENT'')'
    - name: should include PRAGMA foreign_keys directive in SQLite schema
      assertions:
        - 'assertStartsWith(sqliteSchema, ''PRAGMA foreign_keys = ON;'')'
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Database Schema for All Asset Tables (Dual-DB Support)

## Context

The system requires a comprehensive database schema including the base asset table, extension tables for each asset type, attachment table, maintenance tables, and code generation tables. The schema must support both SQLite (development) and MySQL (production) databases.

## Acceptance Criteria

1. Create `sql/schema-mysql.sql` for production environment (MySQL)
2. Create `sql/schema-sqlite.sql` for development environment (SQLite)
3. Both schemas must create identical table structures with dialect-appropriate syntax
4. Create `t_asset` base table with all common fields (id, asset_code, asset_name, asset_type, project_id, status, etc.)
5. Create extension tables: `t_asset_house`, `t_asset_parking`, `t_asset_facility`, `t_asset_venue`, `t_asset_office`
6. Create `t_asset_attachment` for file attachments
7. Create maintenance tables: `t_asset_maint_order`, `t_asset_maint_log`
8. Create code generation tables: `t_asset_code_rule`, `t_asset_code_seq`
9. Create P1 table: `t_asset_maint_plan` for maintenance scheduling
10. Add appropriate indexes: `(project_id, asset_type, status)`, `asset_code` unique
11. Add foreign key constraints where applicable
12. Include audit fields: `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`
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
