---
id: asset-office.database-schema
module: asset-office
priority: 601
status: passing
version: 3
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
    pattern: tests/asset-office/**/*.test.*
---
# Create Office Asset Extension Table

## Context

Extension table for office-specific fields (furniture, IT equipment). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_office` table with office-specific fields:
2. Add foreign key constraint to t_asset.id
3. SQL migration file created at `sql/asset_office.sql`
4. Add dictionary entries for office_type:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Office Asset Module)
- PRD: docs/PRD.md Section 6.6 (办公资产模块)
