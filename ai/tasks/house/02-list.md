---
id: house.list
module: house
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [house.entity, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset List with Search and Filter

## Context

The house asset list is the main entry point for managing house assets. It supports filtering by project, building, floor, status, and area range.

## Acceptance Criteria

1. Backend API `GET /asset/house/list`:
   - Query parameters: projectId, building, floor, roomNo, status, ownershipType, minArea, maxArea, keyword
   - Pagination: pageNum, pageSize
   - Data permission applied via @DataScope
   - Returns paginated AssetHouseVO list
2. Backend service `AssetHouseService.selectHouseList()`:
   - Builds dynamic query conditions
   - Joins t_asset with t_asset_house
   - Includes project/department names
3. Frontend page `views/asset/house/index.vue`:
   - Search form with filters:
     - Project dropdown
     - Building/Floor/Room inputs
     - Status dropdown (dictionary)
     - Ownership type dropdown
     - Area range inputs
   - Data table with columns:
     - Asset code, name, project
     - Building, floor, room
     - Area, status, responsible person
   - Row actions: View, Edit, Initiate Maintenance
4. Toolbar buttons:
   - Add new (with permission check)
   - Export Excel
   - Batch update (responsibility person, department)
5. Responsive design for 1920x1080

## Technical Notes

- Use Element UI Table with pagination
- Dictionary components for dropdowns
- Export uses RuoYi's Excel annotation
