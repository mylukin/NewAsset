---
id: house.frontend-list
module: house
priority: 16
status: passing
version: 23
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
verification:
  verifiedAt: '2025-12-14T12:25:25.665Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 597dc8307e75b515caa3d299cc7da01cf693d871
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:24:05.077Z'
  generatedBy: claude
  forVersion: 20
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/ruoyi/asset/house/controller/HouseAssetControllerTest.java
    e2e:
      - e2e/house/house-list.spec.ts
  unitTestCases:
    - name: >-
        should render house list page component at
        /src/views/asset/house/index.vue
      assertions:
        - assertNotNull(component)
        - assertTrue(Files.exists(Path.of('src/views/asset/house/index.vue')))
    - name: should implement search and filter area with form inputs
      assertions:
        - assertNotNull(searchForm)
        - 'assertTrue(searchForm.hasFields(''assetName'', ''location'', ''status''))'
    - name: should display data table with required columns
      assertions:
        - assertNotNull(dataTable)
        - 'assertEquals(expectedColumns, tableColumns)'
        - >-
          assertTrue(columns.contains('assetName', 'location', 'area',
          'status'))
    - name: should render action buttons for CRUD operations
      assertions:
        - assertNotNull(actionButtons)
        - assertTrue(hasButton('add'))
        - assertTrue(hasButton('edit'))
        - assertTrue(hasButton('delete'))
    - name: should use RuoYi table components with pagination
      assertions:
        - assertNotNull(pagination)
        - assertTrue(usesRuoYiComponents)
        - 'assertEquals(expectedPageSize, pagination.pageSize)'
    - name: should apply v-hasPermi directive for permission control
      assertions:
        - assertTrue(template.contains('v-hasPermi'))
        - 'assertPermissionDirectiveApplied(''system:house:add'')'
        - 'assertPermissionDirectiveApplied(''system:house:edit'')'
    - name: should support column show/hide configuration
      assertions:
        - assertNotNull(columnSettings)
        - assertTrue(canToggleColumnVisibility)
        - 'assertEquals(expectedVisibleColumns, visibleColumns)'
  e2eScenarios:
    - name: user can view house asset list page
      steps:
        - navigate to /asset/house
        - wait for page load
        - verify table is visible
        - verify columns are displayed
    - name: user can search and filter house assets
      steps:
        - navigate to /asset/house
        - enter search term in asset name field
        - click search button
        - verify filtered results are displayed
    - name: user can use action buttons based on permissions
      steps:
        - navigate to /asset/house
        - verify add button visibility based on permission
        - click add button
        - verify add dialog or form opens
    - name: user can paginate through house asset list
      steps:
        - navigate to /asset/house
        - verify pagination controls are visible
        - click next page
        - verify page changes and new data loads
    - name: user can configure visible columns
      steps:
        - navigate to /asset/house
        - open column settings
        - toggle column visibility
        - verify column is hidden/shown in table
  frameworkHint: surefire
---
# Create House Asset List Page

## Context

Vue component for displaying house asset list with search, filter, and actions.

## Acceptance Criteria

1. Create `/src/views/asset/house/index.vue`
2. Implement search/filter area:
3. Implement data table with columns:
4. Implement action buttons:
5. Use RuoYi table components and pagination
6. Apply `v-hasPermi` directive for permission control
7. Support column show/hide configuration
## Technical Notes

- Reference: TECH.md Section 7.2
- Pattern: RuoYi list page pattern
- Components: el-table, el-pagination, dict components
