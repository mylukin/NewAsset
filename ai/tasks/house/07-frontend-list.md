---
id: house.frontend-list
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset List Page

## Context

The house asset list page displays all house assets with filtering, pagination, and action buttons.

## Acceptance Criteria

1. Create `/src/views/asset/house/index.vue`:

2. Implement search/filter section:
   - Project selector (dropdown)
   - Building input
   - Floor input
   - Status selector (dict: asset_status)
   - Ownership type selector (dict: asset_ownership_type)
   - Area range inputs (min/max)
   - Search and Reset buttons

3. Implement action toolbar:
   - Add button (with permission `asset:house:add`)
   - Export button (with permission `asset:house:export`)
   - Batch delete button (with permission `asset:house:remove`)

4. Implement data table with columns:
   - Asset Code (资产编码)
   - Asset Name (资产名称)
   - Project (所属项目)
   - Building/Floor/Room (位置)
   - Building Area (建筑面积)
   - Status (使用状态) - with tag color
   - Current Usage (当前用途)
   - Duty Person (责任人)
   - Create Time (创建时间)
   - Actions: View, Edit, Delete, Initiate Maintenance

5. Implement pagination component

6. Use RuoYi's `v-hasPermi` directive for button permissions

7. Support column show/hide configuration (optional P1)

8. Implement row click to show detail drawer/dialog

## Technical Notes

- Reference: TECH.md section 7.2
- Use Element UI table, form, pagination components
