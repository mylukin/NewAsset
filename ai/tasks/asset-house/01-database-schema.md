---
id: asset-house.database-schema
module: asset-house
priority: 201
status: passing
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
    pattern: tests/asset-house/**/*.test.*
verification:
  verifiedAt: '2025-12-15T13:08:36.230Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 96183823449aa5b14e0ac004815c98cd0595ba2e
  summary: 4/4 criteria satisfied
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
