---
id: core.database-schema
module: core
priority: 101
status: passing
version: 6
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
  generatedAt: '2025-12-15T22:54:37.368Z'
  generatedBy: claude
  forVersion: 5
  suggestedTestFiles:
    unit:
      - tests/core/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should create t_asset table with all common fields
      assertions:
        - expect(tableExists('t_asset')).toBe(true)
        - 'expect(hasColumn(''t_asset'', ''id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''asset_code'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''asset_name'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''project_id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''asset_type'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''status'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''created_at'')).toBe(true)'
        - 'expect(hasColumn(''t_asset'', ''updated_at'')).toBe(true)'
    - name: should create t_asset_location table with 3-level hierarchy
      assertions:
        - expect(tableExists('t_asset_location')).toBe(true)
        - 'expect(hasColumn(''t_asset_location'', ''id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_location'', ''parent_id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_location'', ''level'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_location'', ''name'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_location'', ''path'')).toBe(true)'
    - name: should create t_asset_code_seq table for sequence management
      assertions:
        - expect(tableExists('t_asset_code_seq')).toBe(true)
        - 'expect(hasColumn(''t_asset_code_seq'', ''id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_code_seq'', ''prefix'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_code_seq'', ''current_seq'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_code_seq'', ''year'')).toBe(true)'
    - name: should create t_asset_attachment table
      assertions:
        - expect(tableExists('t_asset_attachment')).toBe(true)
        - 'expect(hasColumn(''t_asset_attachment'', ''id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_attachment'', ''asset_id'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_attachment'', ''file_name'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_attachment'', ''file_path'')).toBe(true)'
        - 'expect(hasColumn(''t_asset_attachment'', ''file_type'')).toBe(true)'
    - name: should have idx_asset_code index on t_asset
      assertions:
        - 'expect(indexExists(''t_asset'', ''idx_asset_code'')).toBe(true)'
    - name: should have idx_project_type_status index on t_asset
      assertions:
        - 'expect(indexExists(''t_asset'', ''idx_project_type_status'')).toBe(true)'
    - name: should have SQL migration file at sql/asset_core.sql
      assertions:
        - expect(fs.existsSync('sql/asset_core.sql')).toBe(true)
        - expect(migrationContent).toContain('CREATE TABLE')
        - expect(migrationContent).toContain('t_asset')
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
