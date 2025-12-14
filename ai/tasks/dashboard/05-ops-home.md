---
id: dashboard.ops-home
module: dashboard
priority: 73
status: passing
version: 24
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
  verifiedAt: '2025-12-14T14:03:54.370Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 458df909cbad03727d6c9553032d2ff9411f58b8
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T14:02:55.775Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/dashboard/ops-home.test.ts
    e2e:
      - e2e/dashboard/ops-home.spec.ts
  unitTestCases:
    - name: should create ops.vue component at correct path
      assertions:
        - expect(component).toBeDefined()
        - expect(component.name).toBe('OpsHome')
    - name: should render pending orders list with order data
      assertions:
        - expect(wrapper.find('.pending-orders-list').exists()).toBe(true)
        - expect(wrapper.findAll('.order-item')).toHaveLength(mockOrders.length)
    - name: should display my monthly stats correctly
      assertions:
        - expect(wrapper.find('.monthly-stats').exists()).toBe(true)
        - expect(wrapper.find('.stats-count').text()).toContain(expectedCount)
    - name: should provide quick link to full order list
      assertions:
        - 'expect(wrapper.find(''a[href*="orders"]'').exists()).toBe(true)'
        - 'expect(router.push).toHaveBeenCalledWith({ name: ''OrderList'' })'
    - name: should auto-refresh data every 5 minutes
      assertions:
        - 'expect(setInterval).toHaveBeenCalledWith(expect.any(Function), 300000)'
        - expect(fetchData).toHaveBeenCalledTimes(2)
    - name: should highlight urgent orders with visual indicator
      assertions:
        - expect(wrapper.find('.order-item.urgent').exists()).toBe(true)
        - >-
          expect(wrapper.find('.order-item.urgent').classes()).toContain('highlight')
  e2eScenarios:
    - name: user views pending orders on ops dashboard
      steps:
        - navigate to /asset/dashboard/ops
        - wait for orders to load
        - verify pending orders list is visible
        - verify order count matches expected
    - name: user views monthly statistics
      steps:
        - navigate to /asset/dashboard/ops
        - locate monthly stats section
        - verify stats values are displayed
        - verify stats labels are correct
    - name: user navigates to full order list via quick link
      steps:
        - navigate to /asset/dashboard/ops
        - click on 'view all orders' link
        - verify navigation to orders page
        - verify orders list is displayed
    - name: user sees urgent orders highlighted
      steps:
        - navigate to /asset/dashboard/ops
        - wait for orders to load
        - locate urgent order in list
        - verify urgent order has highlight styling
    - name: dashboard auto-refreshes after 5 minutes
      steps:
        - navigate to /asset/dashboard/ops
        - note initial order count
        - wait 5 minutes or mock timer
        - verify data refresh occurred
        - verify updated data displayed
  frameworkHint: vitest
---
# Create Operations Dashboard Home Page

## Context

Dashboard for operations/maintenance staff showing their work queue and statistics.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/ops.vue`
2. Implement pending orders list:
3. Implement my monthly stats:
4. Quick link to full order list
5. Auto-refresh every 5 minutes
6. Highlight urgent orders
## Technical Notes

- Reference: PRD Section 7.1 Point 2
- Pattern: Work queue dashboard
- Components: el-table with action buttons
