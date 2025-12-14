---
id: venue.frontend-list
module: venue
priority: 44
status: passing
version: 24
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:15:32.951Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: f421721ae60eb5b10d8fe0c76941421aa165d49d
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:14:05.936Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/views/asset/venue/__tests__/index.test.ts
    e2e:
      - e2e/venue/list.spec.ts
  unitTestCases:
    - name: should render venue list page at /src/views/asset/venue/index.vue
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(wrapper.find('.venue-list').exists()).toBe(true)
    - name: >-
        should implement search filters for project, venueType,
        currentVenueStatus, and status
      assertions:
        - >-
          expect(wrapper.find('[data-test="filter-project"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-venueType"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-currentVenueStatus"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="filter-status"]').exists()).toBe(true)
    - name: should display data table with required columns
      assertions:
        - expect(wrapper.find('.el-table').exists()).toBe(true)
        - expect(tableHeaders).toContain('项目')
        - expect(tableHeaders).toContain('场馆类型')
        - expect(tableHeaders).toContain('当前状态')
    - name: >-
        should render action buttons: Add, Edit, Delete, View, Initiate
        Maintenance, Export
      assertions:
        - 'expect(wrapper.find(''[data-test="btn-add"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-edit"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-delete"]'').exists()).toBe(true)'
        - 'expect(wrapper.find(''[data-test="btn-view"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="btn-maintenance"]').exists()).toBe(true)
        - 'expect(wrapper.find(''[data-test="btn-export"]'').exists()).toBe(true)'
    - name: should implement permission control with v-hasPermi directive
      assertions:
        - 'expect(wrapper.find(''[v-hasPermi]'').exists()).toBe(true)'
        - expect(mockHasPermiDirective).toHaveBeenCalled()
  e2eScenarios:
    - name: user can view venue list page
      steps:
        - navigate to /asset/venue
        - verify page title displays
        - verify data table is visible
    - name: user can filter venues by project
      steps:
        - navigate to /asset/venue
        - select project from dropdown
        - click search button
        - verify filtered results display
    - name: user can filter venues by venue type
      steps:
        - navigate to /asset/venue
        - select venue type from dropdown
        - click search button
        - verify filtered results display
    - name: user can add a new venue
      steps:
        - navigate to /asset/venue
        - click Add button
        - verify add form/dialog opens
    - name: user can edit an existing venue
      steps:
        - navigate to /asset/venue
        - click Edit button on a row
        - verify edit form opens with venue data
    - name: user can delete a venue
      steps:
        - navigate to /asset/venue
        - click Delete button on a row
        - confirm deletion
        - verify venue removed from list
    - name: user can view venue details
      steps:
        - navigate to /asset/venue
        - click View button on a row
        - verify detail view opens
    - name: user can initiate venue maintenance
      steps:
        - navigate to /asset/venue
        - click Initiate Maintenance button
        - verify maintenance workflow starts
    - name: user can export venue data
      steps:
        - navigate to /asset/venue
        - click Export button
        - verify export file downloads
    - name: buttons are hidden for users without permissions
      steps:
        - login as user without venue permissions
        - navigate to /asset/venue
        - verify restricted buttons are not visible
  frameworkHint: vitest
---
# Create Venue Asset List Page

## Context

Vue component for venue asset list.

## Acceptance Criteria

1. Create `/src/views/asset/venue/index.vue`
2. Implement search filters: project, venueType, currentVenueStatus, status
3. Data table columns:
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi
## Technical Notes

- Reference: PRD Section 6.5.2
- Pattern: RuoYi list page
