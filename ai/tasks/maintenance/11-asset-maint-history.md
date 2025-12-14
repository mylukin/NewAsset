---
id: maintenance.asset-maint-history
module: maintenance
priority: 67
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.order-crud-service
supersedes: []
tags:
  - integration
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:49:59.227Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: d02277ccab0016b68e661d5ed5fc6b3d196a6d88
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:49:16.201Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/maintenance/asset-maint-history.test.ts
    e2e:
      - e2e/maintenance/asset-maint-history.spec.ts
  unitTestCases:
    - name: should render AssetMaintHistory component
      assertions:
        - expect(wrapper.exists()).toBe(true)
        - expect(wrapper.find('.asset-maint-history').exists()).toBe(true)
    - name: should accept assetId as prop and fetch maintenance orders
      assertions:
        - expect(wrapper.props('assetId')).toBe('asset-123')
        - expect(mockFetchMaintenanceOrders).toHaveBeenCalledWith('asset-123')
    - name: should display list of related maintenance orders with correct fields
      assertions:
        - expect(wrapper.findAll('.maintenance-order-item')).toHaveLength(3)
        - 'expect(wrapper.text()).toContain(''Work Order #001'')'
        - expect(wrapper.text()).toContain('Preventive Maintenance')
    - name: should filter maintenance orders by status
      assertions:
        - expect(filteredOrders).toHaveLength(2)
        - expect(filteredOrders.every(o => o.status === 'completed')).toBe(true)
    - name: should filter maintenance orders by type
      assertions:
        - expect(filteredOrders).toHaveLength(1)
        - 'expect(filteredOrders[0].type).toBe(''corrective'')'
    - name: should render link to full order detail page
      assertions:
        - >-
          expect(wrapper.find('.order-detail-link').attributes('href')).toContain('/maintenance/orders/')
        - expect(wrapper.find('.order-detail-link').exists()).toBe(true)
    - name: should export maintenance history to Excel
      assertions:
        - expect(mockExportToExcel).toHaveBeenCalled()
        - >-
          expect(mockExportToExcel).toHaveBeenCalledWith(expect.arrayContaining([expect.objectContaining({
          assetId: 'asset-123' })]))
    - name: should integrate into asset detail drawer components
      assertions:
        - >-
          expect(assetDetailDrawer.findComponent(AssetMaintHistory).exists()).toBe(true)
        - >-
          expect(assetDetailDrawer.findComponent(AssetMaintHistory).props('assetId')).toBe('asset-456')
  e2eScenarios:
    - name: user views maintenance history in asset detail drawer
      steps:
        - navigate to asset list page
        - click on an asset to open detail drawer
        - verify maintenance history section is visible
        - verify maintenance orders are displayed
    - name: user filters maintenance history by status
      steps:
        - open asset detail drawer
        - locate maintenance history section
        - select 'Completed' from status filter dropdown
        - verify only completed maintenance orders are shown
    - name: user filters maintenance history by type
      steps:
        - open asset detail drawer
        - locate maintenance history section
        - select 'Preventive' from type filter dropdown
        - verify only preventive maintenance orders are shown
    - name: user navigates to full maintenance order detail
      steps:
        - open asset detail drawer
        - locate maintenance history section
        - click on a maintenance order link
        - verify navigation to maintenance order detail page
    - name: user exports maintenance history to Excel
      steps:
        - open asset detail drawer
        - locate maintenance history section
        - click export to Excel button
        - verify Excel file download is triggered
  frameworkHint: vitest
---
# Integrate Maintenance History into Asset Detail Pages

## Context

Each asset detail page should display its maintenance history from the maintenance module.

## Acceptance Criteria

1. Create reusable component: `AssetMaintHistory.vue`
2. Accept assetId as prop
3. Display list of related maintenance orders:
4. Support filtering by status and type
5. Link to full order detail
6. Support Excel export of history
7. Integrate into all asset detail drawers:
## Technical Notes

- Reference: PRD Section 6.1.2 Point 2
- Pattern: Reusable component
- API: GET /asset/maint/order/asset/{assetId}
