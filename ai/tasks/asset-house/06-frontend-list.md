---
id: asset-house.frontend-list
module: asset-house
priority: 206
status: failing
version: 5
origin: spec-workflow
dependsOn:
  - asset-house.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@house'
verification:
  verifiedAt: '2025-12-15T13:47:02.822Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ba9795eec250025d8e049885332816021e849bcd
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:44:54.919Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/api/asset/house.test.js
      - tests/views/asset/house/index.test.js
    e2e:
      - e2e/asset/house/list.spec.ts
  unitTestCases:
    - name: should export API functions for house asset CRUD operations
      assertions:
        - expect(getHouseAssetList).toBeDefined()
        - expect(typeof getHouseAssetList).toBe('function')
    - name: should render house asset list page with table component
      assertions:
        - expect(wrapper.find('.house-asset-list').exists()).toBe(true)
        - 'expect(wrapper.findComponent({ name: ''ElTable'' }).exists()).toBe(true)'
    - name: should display status filter tabs above table
      assertions:
        - 'expect(wrapper.findComponent({ name: ''ElTabs'' }).exists()).toBe(true)'
        - expect(wrapper.findAll('.el-tabs__item').length).toBeGreaterThan(0)
    - name: should render row action buttons for each asset
      assertions:
        - >-
          expect(wrapper.find('[data-testid="action-view"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-testid="action-edit"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-testid="action-delete"]').exists()).toBe(true)
    - name: should show/hide actions based on user permissions
      assertions:
        - >-
          expect(wrapper.find('[data-testid="action-edit"]').exists()).toBe(hasEditPermission)
        - >-
          expect(wrapper.find('[data-testid="action-delete"]').exists()).toBe(hasDeletePermission)
    - name: should display empty state message when no assets exist
      assertions:
        - expect(wrapper.text()).toContain('No house assets found')
        - >-
          expect(wrapper.text()).toContain("Click 'New' to create your first
          asset")
  e2eScenarios:
    - name: user views house asset list with data
      steps:
        - navigate to /asset/house
        - wait for table to load
        - verify table displays house asset rows
    - name: user filters assets by status tabs
      steps:
        - navigate to /asset/house
        - click on status tab filter
        - verify table updates to show filtered results
    - name: user performs row actions on asset
      steps:
        - navigate to /asset/house
        - locate row action buttons
        - click view action and verify navigation
        - click edit action and verify navigation
    - name: user without permissions sees restricted actions
      steps:
        - login as user without edit/delete permissions
        - navigate to /asset/house
        - verify edit and delete buttons are hidden or disabled
    - name: user sees empty state when no assets exist
      steps:
        - navigate to /asset/house with empty data
        - verify empty state message is displayed
        - verify 'New' button call-to-action is present
  frameworkHint: vitest
---
# Implement House Asset List Page (Frontend)

## Context

Vue page for house asset list. Follows RuoYi-Vue patterns with Element UI components.

## Acceptance Criteria

1. Create API file `src/api/asset/house.js`:
2. Create list page `src/views/asset/house/index.vue`:
3. Status filter tabs above table:
4. Row actions:
5. Permission controls:
6. Empty state: "No house assets found. Click 'New' to create your first asset."
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset List Page)
- Follow RuoYi-Vue list page patterns
