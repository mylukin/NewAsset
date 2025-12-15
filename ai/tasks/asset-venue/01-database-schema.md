---
id: asset-venue.database-schema
module: asset-venue
priority: 501
status: passing
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
    pattern: tests/asset-venue/**/*.test.*
---
# Create Venue Asset Extension Table

## Context

Extension table for venue-specific fields (community spaces). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_venue` table with venue-specific fields:
2. Add foreign key constraint to t_asset.id
3. SQL migration file created at `sql/asset_venue.sql`
4. Add dictionary entries:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Venue Asset Module)
- PRD: docs/PRD.md Section 6.5 (场馆资产模块)
