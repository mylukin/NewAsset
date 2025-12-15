---
id: core.database-schema
module: core
priority: 101
status: passing
version: 8
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
  generatedAt: '2025-12-15T23:25:25.068Z'
  generatedBy: codex
  forVersion: 7
  suggestedTestFiles:
    unit:
      - tests/core/database-schema.test.ts
    e2e:
      - e2e/core/database-schema.spec.ts
  unitTestCases:
    - name: should create t_asset table with common fields
      assertions:
        - expect(sql).toMatch(/CREATE\s+TABLE\s+IF\s+NOT\s+EXISTS\s+t_asset\b/i)
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\bid\b[\s\S]*PRIMARY\s+KEY/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\basset_code\b/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\bproject_id\b/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\basset_type\b/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\bstatus\b/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\bcreated_at\b/i)'
        - 'expect(sql).toMatch(/\bt_asset\b[\s\S]*\bupdated_at\b/i)'
    - name: should create t_asset_location table for 3-level hierarchy
      assertions:
        - >-
          expect(sql).toMatch(/CREATE\s+TABLE\s+IF\s+NOT\s+EXISTS\s+t_asset_location\b/i)
        - >-
          expect(sql).toMatch(/\bt_asset_location\b[\s\S]*\bid\b[\s\S]*PRIMARY\s+KEY/i)
        - 'expect(sql).toMatch(/\bt_asset_location\b[\s\S]*\basset_id\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_location\b[\s\S]*\blevel\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_location\b[\s\S]*\bparent_id\b/i)'
        - >-
          expect(sql).toMatch(/\bt_asset_location\b[\s\S]*\b(level\s*<=\s*3|level\s+IN\s*\(\s*1\s*,\s*2\s*,\s*3\s*\))/i)
    - name: should create t_asset_code_seq table for sequence management
      assertions:
        - >-
          expect(sql).toMatch(/CREATE\s+TABLE\s+IF\s+NOT\s+EXISTS\s+t_asset_code_seq\b/i)
        - >-
          expect(sql).toMatch(/\bt_asset_code_seq\b[\s\S]*\bid\b[\s\S]*PRIMARY\s+KEY/i)
        - 'expect(sql).toMatch(/\bt_asset_code_seq\b[\s\S]*\bproject_id\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_code_seq\b[\s\S]*\basset_type\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_code_seq\b[\s\S]*\bcurrent_seq\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_code_seq\b[\s\S]*\bupdated_at\b/i)'
    - name: should create t_asset_attachment table
      assertions:
        - >-
          expect(sql).toMatch(/CREATE\s+TABLE\s+IF\s+NOT\s+EXISTS\s+t_asset_attachment\b/i)
        - >-
          expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\bid\b[\s\S]*PRIMARY\s+KEY/i)
        - 'expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\basset_id\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\bfile_name\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\bfile_url\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\bmime_type\b/i)'
        - 'expect(sql).toMatch(/\bt_asset_attachment\b[\s\S]*\bsize_bytes\b/i)'
    - name: should add required indexes on t_asset
      assertions:
        - >-
          expect(sql).toMatch(/CREATE\s+INDEX\s+IF\s+NOT\s+EXISTS\s+idx_asset_code\s+ON\s+t_asset\s*\(\s*asset_code\s*\)/i)
        - >-
          expect(sql).toMatch(/CREATE\s+INDEX\s+IF\s+NOT\s+EXISTS\s+idx_project_type_status\s+ON\s+t_asset\s*\(\s*project_id\s*,\s*asset_type\s*,\s*status\s*\)/i)
    - name: should create SQL migration file at sql/asset_core.sql
      assertions:
        - expect(existsSync('sql/asset_core.sql')).toBe(true)
        - expect(sql.length).toBeGreaterThan(0)
        - expect(sql).toMatch(/t_asset\b/i)
        - expect(sql).toMatch(/t_asset_location\b/i)
        - expect(sql).toMatch(/t_asset_code_seq\b/i)
        - expect(sql).toMatch(/t_asset_attachment\b/i)
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
