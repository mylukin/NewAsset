---
id: dashboard.asset-manager-home
module: dashboard
priority: 74
status: passing
version: 23
origin: manual
dependsOn:
  - dashboard.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
verification:
  verifiedAt: '2025-12-14T14:05:17.828Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6c2eb6c77fe06d0af71418a42864ebf2befecb6d
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T14:04:13.080Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/dashboard/assetManager.test.ts
    e2e:
      - e2e/dashboard/assetManager.spec.ts
  unitTestCases:
    - name: should render assetManager.vue component at correct path
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(router.currentRoute.value.path).toBe('/asset/dashboard')
    - name: should display incomplete assets list with pending items
      assertions:
        - expect(wrapper.find('.incomplete-assets-list').exists()).toBe(true)
        - >-
          expect(wrapper.findAll('.incomplete-asset-item')).toHaveLength(expectedCount)
    - name: should display recent assets list with latest entries
      assertions:
        - expect(wrapper.find('.recent-assets-list').exists()).toBe(true)
        - >-
          expect(wrapper.findAll('.recent-asset-item').length).toBeGreaterThan(0)
    - name: should render asset distribution chart with correct data
      assertions:
        - expect(wrapper.find('.asset-distribution-chart').exists()).toBe(true)
        - expect(chartComponent.props('data')).toEqual(expectedChartData)
    - name: should render project selector and handle selection changes
      assertions:
        - expect(wrapper.find('.project-selector').exists()).toBe(true)
        - expect(wrapper.emitted('project-change')).toBeTruthy()
    - name: >-
        should display loading state while fetching data and empty state when no
        data
      assertions:
        - expect(wrapper.find('.loading-spinner').exists()).toBe(true)
        - expect(wrapper.find('.empty-state').exists()).toBe(true)
  e2eScenarios:
    - name: user views asset manager dashboard
      steps:
        - navigate to /asset/dashboard
        - verify page title displays correctly
        - verify dashboard layout renders
    - name: user views incomplete assets list
      steps:
        - navigate to asset manager dashboard
        - locate incomplete assets section
        - verify incomplete assets are displayed with status indicators
    - name: user views recent assets list
      steps:
        - navigate to asset manager dashboard
        - locate recent assets section
        - verify recent assets show with timestamps
    - name: user views asset distribution chart
      steps:
        - navigate to asset manager dashboard
        - locate distribution chart
        - verify chart renders with correct categories
    - name: user selects different project from selector
      steps:
        - navigate to asset manager dashboard
        - click project selector dropdown
        - select different project
        - verify dashboard data updates
    - name: user sees loading and empty states
      steps:
        - navigate to asset manager dashboard with slow network
        - verify loading indicator appears
        - clear all assets
        - verify empty state message displays
  frameworkHint: vitest
---
# Create Asset Manager Dashboard Home Page

## Context

Dashboard for asset managers showing assets needing attention and recent additions.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/assetManager.vue`
2. Implement incomplete assets list:
3. Implement recent assets list:
4. Implement asset distribution chart:
5. Add project selector
6. Loading and empty states
## Technical Notes

- Reference: PRD Section 7.1 Point 3
- Pattern: Admin dashboard
- Components: el-table, ECharts
