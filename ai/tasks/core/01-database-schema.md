---
id: core.database-schema
module: core
priority: 101
status: passing
version: 3
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
  verifiedAt: '2025-12-15T11:39:14.981Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: unknown
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T11:37:49.520Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should create t_asset table with all common fields
      assertions:
        - expect(tableExists('t_asset')).toBe(true)
        - expect(getTableColumns('t_asset')).toContain('id')
        - expect(getTableColumns('t_asset')).toContain('asset_code')
        - expect(getTableColumns('t_asset')).toContain('project_id')
        - expect(getTableColumns('t_asset')).toContain('asset_type')
        - expect(getTableColumns('t_asset')).toContain('status')
    - name: should create t_asset_location table with 3-level hierarchy
      assertions:
        - expect(tableExists('t_asset_location')).toBe(true)
        - expect(getTableColumns('t_asset_location')).toContain('level')
        - expect(getTableColumns('t_asset_location')).toContain('parent_id')
        - expect(getTableColumns('t_asset_location')).toContain('name')
    - name: should create t_asset_code_seq table for sequence management
      assertions:
        - expect(tableExists('t_asset_code_seq')).toBe(true)
        - expect(getTableColumns('t_asset_code_seq')).toContain('project_id')
        - expect(getTableColumns('t_asset_code_seq')).toContain('asset_type')
        - expect(getTableColumns('t_asset_code_seq')).toContain('current_seq')
    - name: should create t_asset_attachment table
      assertions:
        - expect(tableExists('t_asset_attachment')).toBe(true)
        - expect(getTableColumns('t_asset_attachment')).toContain('asset_id')
        - expect(getTableColumns('t_asset_attachment')).toContain('file_path')
        - expect(getTableColumns('t_asset_attachment')).toContain('file_name')
    - name: should add required indexes on t_asset table
      assertions:
        - 'expect(indexExists(''t_asset'', ''idx_asset_code'')).toBe(true)'
        - 'expect(indexExists(''t_asset'', ''idx_project_type_status'')).toBe(true)'
    - name: should create SQL migration file at correct path
      assertions:
        - expect(fileExists('sql/asset_core.sql')).toBe(true)
        - expect(readFile('sql/asset_core.sql')).toContain('CREATE TABLE')
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
