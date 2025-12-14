---
id: house.frontend-list
module: house
priority: 16
status: failing
version: 9
origin: manual
dependsOn:
  - house.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset List Page

## Context

Vue component for displaying house asset list with search, filter, and actions.

## Acceptance Criteria

1. Create `/src/views/asset/house/index.vue`
2. Implement search/filter area:
   - Project selector (dict)
   - Building/Floor inputs
   - Status dropdown (dict: asset_status)
   - House type dropdown (dict: house_type)
   - Current usage dropdown
   - Area range inputs
3. Implement data table with columns:
   - Asset code, Asset name, Project, Building, Floor, Room
   - Area (building/inner), House type, Status, Current usage
   - Duty user, Actions
4. Implement action buttons:
   - Add (with permission check)
   - Edit, Delete, View detail
   - Initiate maintenance
   - Export to Excel
5. Use RuoYi table components and pagination
6. Apply `v-hasPermi` directive for permission control
7. Support column show/hide configuration

## Technical Notes

- Reference: TECH.md Section 7.2
- Pattern: RuoYi list page pattern
- Components: el-table, el-pagination, dict components
