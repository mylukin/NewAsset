---
id: asset-facility.database-schema
module: asset-facility
priority: 401
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
    pattern: tests/asset-facility/**/*.test.*
---
# Create Facility Asset Extension Table

## Context

Extension table for facility-specific fields (residential equipment/facilities). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_facility` table with facility-specific fields:
2. Add foreign key constraint to t_asset.id
3. SQL migration file created at `sql/asset_facility.sql`
4. Add dictionary entries for facility_type:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Facility Asset Module)
- PRD: docs/PRD.md Section 6.3 (住宅配套资产模块)
