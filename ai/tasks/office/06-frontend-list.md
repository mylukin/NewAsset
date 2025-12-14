---
id: office.frontend-list
module: office
priority: 53
status: passing
version: 24
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:30:15.434Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 21b22e40e2dd9a0344d51837085d80a5ec14f574
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:28:40.541Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/office/controller/OfficeAssetControllerTest.java
    e2e:
      - e2e/office/office-asset-list.spec.ts
  unitTestCases:
    - name: should create office asset list page at correct path
      assertions:
        - assertThat(new File('src/views/asset/office/index.vue')).exists()
        - assertThat(component).isNotNull()
    - name: >-
        should implement search filters for project, useDeptId, officeType,
        dutyUserId, status
      assertions:
        - >-
          assertThat(searchForm.getFields()).containsExactlyInAnyOrder('project',
          'useDeptId', 'officeType', 'dutyUserId', 'status')
        - assertThat(filterComponent.isFilterable()).isTrue()
    - name: should display data table with required columns
      assertions:
        - assertThat(tableColumns).isNotEmpty()
        - assertThat(tableComponent.getColumnCount()).isGreaterThan(0)
    - name: >-
        should render action buttons Add, Edit, Delete, View, Initiate
        Maintenance, Export
      assertions:
        - >-
          assertThat(actionButtons).containsExactlyInAnyOrder('Add', 'Edit',
          'Delete', 'View', 'Initiate Maintenance', 'Export')
        - assertThat(buttonGroup.getButtonCount()).isEqualTo(6)
    - name: should apply v-hasPermi permission control on actions
      assertions:
        - assertThat(addButton.hasDirective('v-hasPermi')).isTrue()
        - assertThat(deleteButton.hasDirective('v-hasPermi')).isTrue()
  e2eScenarios:
    - name: user can view office asset list page
      steps:
        - navigate to /asset/office
        - verify page title displays
        - verify data table is visible
    - name: user can filter office assets by search criteria
      steps:
        - navigate to /asset/office
        - enter project filter value
        - select useDeptId from dropdown
        - select officeType from dropdown
        - click search button
        - verify filtered results display
    - name: user can add new office asset
      steps:
        - navigate to /asset/office
        - click Add button
        - verify add dialog or page opens
    - name: user can edit existing office asset
      steps:
        - navigate to /asset/office
        - click Edit button on first row
        - verify edit dialog or page opens with asset data
    - name: user can delete office asset
      steps:
        - navigate to /asset/office
        - click Delete button on first row
        - confirm deletion
        - verify asset removed from list
    - name: user can view office asset details
      steps:
        - navigate to /asset/office
        - click View button on first row
        - verify detail view displays asset information
    - name: user can initiate maintenance for office asset
      steps:
        - navigate to /asset/office
        - click Initiate Maintenance button
        - verify maintenance form or dialog opens
    - name: user can export office assets
      steps:
        - navigate to /asset/office
        - click Export button
        - verify export file downloads
    - name: user without permission cannot see restricted action buttons
      steps:
        - 'login as user without asset:office:add permission'
        - navigate to /asset/office
        - verify Add button is not visible
  frameworkHint: surefire
---
# Create Office Asset List Page

## Context

Vue component for office asset list.

## Acceptance Criteria

1. Create `/src/views/asset/office/index.vue`
2. Implement search filters: project, useDeptId, officeType, dutyUserId, status
3. Data table columns:
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi
## Technical Notes

- Reference: PRD Section 6.6.2
- Pattern: RuoYi list page
