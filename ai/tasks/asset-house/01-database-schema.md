---
id: asset-house.database-schema
module: asset-house
priority: 201
status: failing
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
    pattern: tests/asset-house/**/*.test.*
tddGuidance:
  generatedAt: '2025-12-15T13:10:01.479Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-house/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should define t_asset_house table creation in SQL
      assertions:
        - expect(sqlContent).toMatch(/CREATE TABLE.*?t_asset_house/s)
    - name: should include foreign key constraint to t_asset
      assertions:
        - expect(sqlContent).toMatch(/FOREIGN KEY.*?REFERENCES t_asset/)
    - name: should exist at sql/asset_house.sql
      assertions:
        - expect(fs.existsSync('sql/asset_house.sql')).toBe(true)
    - name: should include dictionary entries for house attributes
      assertions:
        - expect(sqlContent).toContain('house_type')
        - expect(sqlContent).toContain('house_usage')
        - expect(sqlContent).toContain('current_usage')
  e2eScenarios: []
  frameworkHint: vitest
---
# Create House Asset Extension Table

## Context

Extension table for house-specific fields. 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_house` table with house-specific fields:
2. Add foreign key constraint to t_asset.id
3. SQL migration file created at `sql/asset_house.sql`
4. Add dictionary entries for house_type, house_usage, current_usage:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (House Asset Module)
- PRD: docs/PRD.md Section 6.2
