---
id: facility.frontend-list
module: facility
priority: 25
status: failing
version: 18
origin: manual
dependsOn:
  - facility.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset List Page

## Context

Vue component for displaying facility asset list with search and actions.

## Acceptance Criteria

1. Create `/src/views/asset/facility/index.vue`
2. Implement search filters: project, building, floor, facilityType, status
3. Implement data table with columns:
   - Asset code, name, project, building, floor
   - Facility type, equipment no, status
   - Maintenance org, actions
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Use permission directive for access control
6. Support Excel export

## Technical Notes

- Reference: PRD Section 6.3.2
- Pattern: RuoYi list page
- Components: el-table, el-pagination
