---
id: core.database-schema
module: core
priority: 101
status: passing
version: 7
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
  generatedAt: '2025-12-15T23:12:01.186Z'
  generatedBy: claude
  forVersion: 6
  suggestedTestFiles:
    unit:
      - tests/core/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should create t_asset table with all common fields
      assertions:
        - expect(schema).toContain('CREATE TABLE t_asset')
        - expect(assetTableColumns).toContain('id')
        - expect(assetTableColumns).toContain('asset_code')
        - expect(assetTableColumns).toContain('project_type')
        - expect(assetTableColumns).toContain('status')
    - name: should create t_asset_location table with 3-level hierarchy
      assertions:
        - expect(schema).toContain('CREATE TABLE t_asset_location')
        - expect(locationTableColumns).toContain('level')
        - expect(locationTableColumns).toContain('parent_id')
    - name: should create t_asset_code_seq table for sequence management
      assertions:
        - expect(schema).toContain('CREATE TABLE t_asset_code_seq')
        - expect(seqTableColumns).toContain('prefix')
        - expect(seqTableColumns).toContain('current_seq')
    - name: should create t_asset_attachment table
      assertions:
        - expect(schema).toContain('CREATE TABLE t_asset_attachment')
        - expect(attachmentTableColumns).toContain('asset_id')
        - expect(attachmentTableColumns).toContain('file_path')
    - name: should add indexes idx_asset_code and idx_project_type_status on t_asset
      assertions:
        - expect(schema).toContain('CREATE INDEX idx_asset_code')
        - expect(schema).toContain('CREATE INDEX idx_project_type_status')
    - name: should have SQL migration file at sql/asset_core.sql
      assertions:
        - expect(fs.existsSync('sql/asset_core.sql')).toBe(true)
        - expect(migrationContent).toBeTruthy()
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
