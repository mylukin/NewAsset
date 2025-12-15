---
id: core.database-schema
module: core
priority: 101
status: failing
version: 5
origin: spec-workflow
dependsOn: []
supersedes: []
tags:
  - backend
  - database
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-15T15:06:49.851Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6a44037e350f96554f6195959d0606d55ac2c995
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T15:06:31.388Z'
  generatedBy: claude
  forVersion: 3
  suggestedTestFiles:
    unit:
      - tests/core/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should create t_asset table with all common fields
      assertions:
        - expect(sqlContent).toContain('CREATE TABLE t_asset')
        - >-
          expect(sqlContent).toMatch(/CREATE TABLE
          t_asset\s*\([^)]*(?:id|name|description|type|status|created_at|updated_at)[^)]*\)/s)
    - name: should create t_asset_location table for 3-level hierarchy
      assertions:
        - expect(sqlContent).toContain('CREATE TABLE t_asset_location')
        - >-
          expect(sqlContent).toMatch(/CREATE TABLE
          t_asset_location\s*\([^)]*(?:location_level|location_parent_id|location_path)[^)]*\)/s)
    - name: should create t_asset_code_seq table for sequence management
      assertions:
        - expect(sqlContent).toContain('CREATE TABLE t_asset_code_seq')
        - >-
          expect(sqlContent).toMatch(/CREATE TABLE
          t_asset_code_seq\s*\([^)]*(?:project_type|sequence_number|current_value)[^)]*\)/s)
    - name: should create t_asset_attachment table
      assertions:
        - expect(sqlContent).toContain('CREATE TABLE t_asset_attachment')
        - >-
          expect(sqlContent).toMatch(/CREATE TABLE
          t_asset_attachment\s*\([^)]*(?:attachment_id|asset_id|file_name|file_path)[^)]*\)/s)
    - name: should add idx_asset_code and idx_project_type_status indexes on t_asset
      assertions:
        - expect(sqlContent).toContain('CREATE INDEX idx_asset_code')
        - expect(sqlContent).toContain('CREATE INDEX idx_project_type_status')
        - >-
          expect(sqlContent).toMatch(/CREATE INDEX idx_asset_code\s+ON
          t_asset/i)
        - >-
          expect(sqlContent).toMatch(/CREATE INDEX idx_project_type_status\s+ON
          t_asset/i)
    - name: should create SQL migration file at sql/asset_core.sql
      assertions:
        - expect(fs.existsSync('sql/asset_core.sql')).toBe(true)
        - 'expect(fs.readFileSync(''sql/asset_core.sql'', ''utf8'')).toBe(sqlContent)'
  e2eScenarios: []
  frameworkHint: vitest
---
# Create Core Database Schema

## Context

Foundation tables for the asset management system. All asset types share a base table with extension tables for type-specific fields.

## Acceptance Criteria

1. Create `t_asset` table with all common fields:
2. Create `t_asset_location` table for 3-level hierarchy:
3. Create `t_asset_code_seq` table for sequence management:
4. Create `t_asset_attachment` table:
5. Add indexes: idx_asset_code, idx_project_type_status on t_asset
6. SQL migration file created at `sql/asset_core.sql`
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design section)
- Use RuoYi convention for audit fields
- All tables use InnoDB engine with utf8mb4 charset
