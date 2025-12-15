---
id: asset-house.frontend-detail
module: asset-house
priority: 207
status: passing
version: 5
origin: spec-workflow
dependsOn:
  - asset-house.frontend-list
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
  verifiedAt: '2025-12-15T14:11:49.229Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e3e42431ff8da67a496d621311a8a476aa74c836
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:53:21.357Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-house/detail.test.ts
    e2e:
      - e2e/asset-house/detail.spec.ts
  unitTestCases:
    - name: should render the detail drawer component
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - >-
          expect(wrapper.findComponent({ name: 'ElDrawer'
          }).exists()).toBe(true)
    - name: should render 3 tabs using el-tabs
      assertions:
        - 'expect(wrapper.findComponent({ name: ''ElTabs'' }).exists()).toBe(true)'
        - >-
          expect(wrapper.findAllComponents({ name: 'ElTabPane'
          })).toHaveLength(3)
    - name: should render header actions
      assertions:
        - expect(wrapper.find('.header-actions').exists()).toBe(true)
    - name: should display status badge
      assertions:
        - expect(wrapper.find('.status-badge').exists()).toBe(true)
    - name: should navigate back to list page when closed
      assertions:
        - await wrapper.vm.handleClose()
        - expect(router.push).toHaveBeenCalled()
  e2eScenarios:
    - name: 'User opens detail drawer, checks tabs, and closes it'
      steps:
        - navigate to asset list page
        - click on an asset item
        - verify detail drawer appears
        - verify status badge is present
        - verify all 3 tabs are clickable
        - click close button
        - verify returned to list page
  frameworkHint: vitest
---
# Implement House Asset Detail Drawer (Frontend)

## Context

Detail drawer component showing full house asset information with tabs.

## Acceptance Criteria

1. Create detail drawer component `src/views/asset/house/detail.vue`:
2. Implement 3 tabs (el-tabs):
3. Header actions:
4. Status badge:
5. Close drawer returns to list page
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset Detail Drawer)
- Uses el-drawer, el-tabs, el-descriptions components
