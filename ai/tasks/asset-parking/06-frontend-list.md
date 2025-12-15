---
id: asset-parking.frontend-list
module: asset-parking
priority: 306
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@parking'
tddGuidance:
  generatedAt: '2025-12-15T14:55:28.205Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/views/asset/parking/index.test.js
      - tests/api/asset/parking.test.js
    e2e:
      - e2e/asset/parking/list.spec.js
  unitTestCases:
    - name: should export required CRUD methods in parking API
      assertions:
        - expect(api.listParking).toBeDefined()
        - expect(api.getParking).toBeDefined()
        - expect(api.addParking).toBeDefined()
        - expect(api.updateParking).toBeDefined()
        - expect(api.delParking).toBeDefined()
    - name: should render the parking list page component
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(wrapper.find('.app-container').exists()).toBe(true)
    - name: should display all status filter tabs
      assertions:
        - expect(wrapper.text()).toContain('All')
        - expect(wrapper.text()).toContain('Self-use')
        - expect(wrapper.text()).toContain('Rented')
        - expect(wrapper.text()).toContain('Idle')
        - expect(wrapper.findAll('.el-tabs__item')).toHaveLength(4)
    - name: should hide action buttons without permissions
      assertions:
        - >-
          expect(wrapper.findComponent({ name: 'RightToolbar'
          }).exists()).toBe(true)
        - >-
          expect(wrapper.find('[v-hasPermi="[\'asset:parking:add\']"]').exists()).toBe(true)
    - name: should show empty text when list is empty
      assertions:
        - expect(wrapper.find('.el-table__empty-text').exists()).toBe(true)
        - expect(wrapper.find('.el-table__empty-text').text()).toBeTruthy()
  e2eScenarios:
    - name: User can view and filter parking assets
      steps:
        - navigate to /asset/parking
        - verify the asset table is displayed
        - click on 'Self-use' status tab
        - verify the table data filters by self-use status
        - click on 'Rented' status tab
        - verify the table data filters by rented status
    - name: User sees appropriate permission-based controls
      steps:
        - login as user with view-only permissions
        - navigate to /asset/parking
        - verify add/edit/delete buttons are not visible
  frameworkHint: vitest
---
# Implement Parking Asset List Page (Frontend)

## Context

Vue page for parking asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/parking.js`:
2. Create list page `src/views/asset/parking/index.vue`:
3. Status filter tabs: All, Self-use, Rented, Idle
4. Permission controls with v-hasPermi
5. Empty state message
## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
