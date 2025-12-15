---
id: asset-parking.database-schema
module: asset-parking
priority: 301
status: failing
version: 4
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
    pattern: tests/asset-parking/**/*.test.*
verification:
  verifiedAt: '2025-12-15T14:04:17.925Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6ac057763544910bf5899df800f9f1f2ad1c1da7
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T14:01:38.991Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-parking/database-schema.test.ts
    e2e: []
  unitTestCases:
    - name: should create t_asset_parking table
      assertions:
        - expect(tableExists('t_asset_parking')).toBe(true)
        - expect(getTableColumns('t_asset_parking')).toContain('id')
    - name: should add foreign key constraint to t_asset.id
      assertions:
        - >-
          expect(foreignKeyExists('t_asset_parking', 'asset_id', 't_asset',
          'id')).toBe(true)
    - name: should create SQL migration file at sql/asset_parking.sql
      assertions:
        - expect(fileExistsSync('sql/asset_parking.sql')).toBe(true)
        - >-
          expect(readFileSync('sql/asset_parking.sql',
          'utf-8')).toContain('CREATE TABLE')
        - >-
          expect(readFileSync('sql/asset_parking.sql',
          'utf-8')).toContain('t_asset_parking')
    - name: should add dictionary entries
      assertions:
        - expect(getDictionaryEntries('parking')).toBeDefined()
        - >-
          expect(Object.keys(getDictionaryEntries('parking')).length).toBeGreaterThan(0)
  e2eScenarios: []
  frameworkHint: vitest
---
# Create Parking Asset Extension Table

## Context

Extension table for parking-specific fields. 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_parking` table:
2. Add foreign key constraint to t_asset.id
3. SQL migration file: `sql/asset_parking.sql`
4. Add dictionary entries:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Parking Asset Module)
- PRD: docs/PRD.md Section 6.4
