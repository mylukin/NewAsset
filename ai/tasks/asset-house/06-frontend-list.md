---
id: asset-house.frontend-list
module: asset-house
priority: 206
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.controller
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@house"
---
# Implement House Asset List Page (Frontend)

## Context

Vue page for house asset list. Follows RuoYi-Vue patterns with Element UI components.

## Acceptance Criteria

1. Create API file `src/api/asset/house.js`:
   - listHouse(query)
   - getHouse(id)
   - addHouse(data)
   - updateHouse(data)
   - delHouse(ids)
   - copyHouse(id)
   - exportHouse(query)
   - getHouseStats(projectId)

2. Create list page `src/views/asset/house/index.vue`:
   - Search bar: Project, building, floor, status, usage, area range, keyword
   - Action bar: New, Import, Export, Batch Delete buttons
   - Data table (el-table):
     - Columns: Checkbox, Asset Code, Name, Building, Floor, Room, Area, Status, Usage, Actions
     - Status column: el-tag with color mapping
     - Actions: View, Edit, Copy, Initiate Maintenance
   - Pagination (el-pagination): 20/50/100 page sizes

3. Status filter tabs above table:
   - All, Self-use, Rented, Idle

4. Row actions:
   - View: Opens detail drawer
   - Edit: Opens edit form drawer
   - Copy: Opens pre-filled create form
   - Initiate Maintenance: Opens work order creation form

5. Permission controls:
   - v-hasPermi="['asset:house:add']" for New button
   - v-hasPermi="['asset:house:edit']" for Edit action
   - v-hasPermi="['asset:house:remove']" for Delete

6. Empty state: "No house assets found. Click 'New' to create your first asset."

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset List Page)
- Follow RuoYi-Vue list page patterns
