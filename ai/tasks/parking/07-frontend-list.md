---
id: parking.frontend-list
module: parking
priority: 35
status: passing
version: 23
origin: manual
dependsOn:
  - parking.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:03:36.293Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6d030d0d0996b6db152d502ed5501f4549ef71d3
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:01:08.603Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/views/asset/parking/__tests__/index.test.ts
    e2e:
      - e2e/asset/parking/list.spec.ts
  unitTestCases:
    - name: should render parking list page component at correct path
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(router.currentRoute.value.path).toBe('/asset/parking')
    - name: should render all search filter fields
      assertions:
        - >-
          expect(wrapper.find('[data-test="filter-project"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-parkingZone"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-parkingNo"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-parkingType"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-status"]').exists()).toBe(true)
    - name: should display data table with required columns
      assertions:
        - expect(wrapper.find('.el-table').exists()).toBe(true)
        - expect(tableColumns).toContain('parkingNo')
        - expect(tableColumns).toContain('parkingZone')
        - expect(tableColumns).toContain('parkingType')
        - expect(tableColumns).toContain('status')
    - name: should render all action buttons
      assertions:
        - 'expect(wrapper.find(''[data-test="btn-add"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-edit"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-delete"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-view"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="btn-maintenance"]').exists()).toBe(true)
        - 'expect(wrapper.find(''[data-test="btn-export"]'').exists()).toBe(true)'
    - name: should apply v-hasPermi directive for permission control
      assertions:
        - >-
          expect(wrapper.find('[data-test="btn-add"]').attributes('v-haspermi')).toBeDefined()
        - 'expect(mockHasPermission).toHaveBeenCalledWith([''asset:parking:add''])'
  e2eScenarios:
    - name: user can view parking asset list page
      steps:
        - navigate to /asset/parking
        - verify page title displays 'Parking Asset List'
        - verify data table is visible with parking records
    - name: user can filter parking assets by criteria
      steps:
        - navigate to /asset/parking
        - select a project from project filter dropdown
        - enter parking number in search field
        - click search button
        - verify table displays filtered results
    - name: user can add new parking asset
      steps:
        - navigate to /asset/parking
        - click Add button
        - verify add dialog or page opens
        - fill in parking asset form
        - submit form
        - verify new parking asset appears in list
    - name: user can edit existing parking asset
      steps:
        - navigate to /asset/parking
        - click Edit button on a row
        - verify edit form displays with existing data
        - modify parking asset details
        - save changes
        - verify updated data displays in table
    - name: user can delete parking asset
      steps:
        - navigate to /asset/parking
        - click Delete button on a row
        - confirm deletion in dialog
        - verify parking asset is removed from list
    - name: user can view parking asset details
      steps:
        - navigate to /asset/parking
        - click View button on a row
        - verify detail view displays all parking asset information
    - name: user can initiate maintenance for parking asset
      steps:
        - navigate to /asset/parking
        - click Initiate Maintenance button on a row
        - verify maintenance form or workflow opens
    - name: user can export parking asset data
      steps:
        - navigate to /asset/parking
        - click Export button
        - verify file download starts or export dialog appears
    - name: buttons are hidden without proper permissions
      steps:
        - 'login as user without parking:add permission'
        - navigate to /asset/parking
        - verify Add button is not visible
        - verify other restricted buttons follow permission rules
  frameworkHint: vitest
---
# Create Parking Asset List Page

## Context

Vue component for parking asset list.

## Acceptance Criteria

1. Create `/src/views/asset/parking/index.vue`
2. Implement search filters: project, parkingZone, parkingNo, parkingType, status
3. Data table columns:
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi
## Technical Notes

- Reference: PRD Section 6.4.2
- Pattern: RuoYi list page
