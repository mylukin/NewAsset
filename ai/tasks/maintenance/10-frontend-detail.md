---
id: maintenance.frontend-detail
module: maintenance
priority: 66
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:48:55.083Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 655e09abfdc813f2f5c435c892e7fe892f14730b
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:47:47.492Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/maintenance/orderDetail.test.ts
    e2e:
      - e2e/maintenance/orderDetail.spec.ts
  unitTestCases:
    - name: should render orderDetail.vue component at correct path
      assertions:
        - expect(component).toBeDefined()
        - 'expect(router.resolve(''/asset/maint/order/:id'')).toBeTruthy()'
    - name: >-
        should display header section with order number, status badge, and
        priority indicator
      assertions:
        - expect(wrapper.find('.order-number').exists()).toBe(true)
        - expect(wrapper.find('.status-badge').exists()).toBe(true)
        - expect(wrapper.find('.priority-indicator').exists()).toBe(true)
    - name: should display basic info section with all required fields
      assertions:
        - >-
          expect(wrapper.find('.basic-info').text()).toContain(mockOrder.orderNumber)
        - expect(wrapper.find('.asset-link').exists()).toBe(true)
        - expect(wrapper.find('.scheduled-date').exists()).toBe(true)
    - name: should render timeline with maintenance history entries
      assertions:
        - >-
          expect(wrapper.findAll('.timeline-item')).toHaveLength(mockHistory.length)
        - expect(wrapper.find('.timeline').exists()).toBe(true)
    - name: should display workflow action buttons based on current status
      assertions:
        - expect(wrapper.find('.action-start').exists()).toBe(orderCanStart)
        - >-
          expect(wrapper.find('.action-complete').exists()).toBe(orderCanComplete)
        - expect(wrapper.find('.action-cancel').exists()).toBe(orderCanCancel)
    - name: should open dialog when workflow action button is clicked
      assertions:
        - expect(wrapper.find('.workflow-dialog').isVisible()).toBe(true)
        - expect(wrapper.find('.dialog-form').exists()).toBe(true)
    - name: should navigate to asset detail page when asset link is clicked
      assertions:
        - >-
          expect(router.push).toHaveBeenCalledWith({ name: 'assetDetail',
          params: { id: mockOrder.assetId } })
  e2eScenarios:
    - name: user views maintenance order detail page
      steps:
        - 'navigate to /asset/maint/order/:id'
        - verify header displays order number and status
        - verify basic info section shows asset name and scheduled date
        - verify timeline shows maintenance history
    - name: user starts maintenance workflow
      steps:
        - navigate to maintenance order detail
        - click start button
        - fill in start dialog form
        - submit form
        - verify status changes to in-progress
    - name: user completes maintenance workflow
      steps:
        - navigate to in-progress maintenance order
        - click complete button
        - fill in completion notes
        - submit form
        - verify status changes to completed
    - name: user navigates to related asset detail
      steps:
        - navigate to maintenance order detail
        - click asset name link
        - verify navigation to asset detail page
  frameworkHint: vitest
---
# Create Maintenance Order Detail Page

## Context

Detail page showing order info, timeline, and workflow actions.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderDetail.vue`
2. Layout sections:
3. Timeline display:
4. Workflow action dialogs:
5. Link to related asset detail
## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: Detail page with timeline
- Component: el-timeline for status history
