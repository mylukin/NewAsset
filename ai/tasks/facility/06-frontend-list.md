---
id: facility.frontend-list
module: facility
priority: 25
status: passing
version: 23
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
verification:
  verifiedAt: '2025-12-14T12:42:56.908Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ece81c0e4daf7d62389403eb042525f76c65eb14
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:41:22.351Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/views/asset/facility/__tests__/index.spec.ts
    e2e:
      - e2e/facility/list.spec.ts
  unitTestCases:
    - name: should render facility list page component at correct path
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(router.currentRoute.value.path).toBe('/asset/facility')
    - name: >-
        should render search filters for project, building, floor, facilityType,
        and status
      assertions:
        - >-
          expect(wrapper.find('[data-test="filter-project"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-building"]').exists()).toBe(true)
        - 'expect(wrapper.find(''[data-test="filter-floor"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="filter-facilityType"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-status"]').exists()).toBe(true)
    - name: should render data table with required columns
      assertions:
        - >-
          expect(wrapper.find('[data-test="facility-table"]').exists()).toBe(true)
        - expect(tableColumns).toContain('facilityCode')
        - expect(tableColumns).toContain('facilityName')
        - expect(tableColumns).toContain('facilityType')
        - expect(tableColumns).toContain('status')
    - name: >-
        should render action buttons for Add, Edit, Delete, View, Initiate
        Maintenance, and Export
      assertions:
        - 'expect(wrapper.find(''[data-test="btn-add"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-edit"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-delete"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-view"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="btn-maintenance"]').exists()).toBe(true)
        - 'expect(wrapper.find(''[data-test="btn-export"]'').exists()).toBe(true)'
    - name: should apply permission directive for access control on action buttons
      assertions:
        - 'expect(wrapper.find(''[v-permission]'').exists()).toBe(true)'
        - 'expect(addButton.attributes(''v-permission'')).toContain(''facility:add'')'
        - >-
          expect(deleteButton.attributes('v-permission')).toContain('facility:delete')
    - name: should support Excel export functionality
      assertions:
        - expect(typeof exportToExcel).toBe('function')
        - >-
          expect(exportToExcel).toHaveBeenCalledWith(expect.objectContaining({
          filename: expect.stringContaining('facility') }))
  e2eScenarios:
    - name: user can view facility list page
      steps:
        - navigate to /asset/facility
        - verify page title contains 'Facility'
        - verify data table is visible
    - name: user can filter facilities by search criteria
      steps:
        - navigate to /asset/facility
        - select project filter
        - select building filter
        - click search button
        - verify table data is filtered
    - name: user can add new facility
      steps:
        - navigate to /asset/facility
        - click Add button
        - verify add dialog/form is displayed
    - name: user can view facility details
      steps:
        - navigate to /asset/facility
        - click View button on a row
        - verify detail view is displayed
    - name: user can export facilities to Excel
      steps:
        - navigate to /asset/facility
        - click Export button
        - verify file download is triggered
    - name: user with insufficient permissions cannot see restricted actions
      steps:
        - login as restricted user
        - navigate to /asset/facility
        - verify Add button is hidden or disabled
        - verify Delete button is hidden or disabled
  frameworkHint: vitest
---
# Create Facility Asset List Page

## Context

Vue component for displaying facility asset list with search and actions.

## Acceptance Criteria

1. Create `/src/views/asset/facility/index.vue`
2. Implement search filters: project, building, floor, facilityType, status
3. Implement data table with columns:
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Use permission directive for access control
6. Support Excel export
## Technical Notes

- Reference: PRD Section 6.3.2
- Pattern: RuoYi list page
- Components: el-table, el-pagination
