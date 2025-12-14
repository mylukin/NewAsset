---
id: maintenance.frontend-list
module: maintenance
priority: 64
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
  verifiedAt: '2025-12-14T13:46:55.263Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 1d8d0713e3df851337bc19a8a74cea011bb5e4b0
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:45:29.162Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/maintenance/OrderListViewTest.java
    e2e:
      - e2e/maintenance/order-list.spec.ts
  unitTestCases:
    - name: shouldCreateOrderListViewFile
      assertions:
        - assertThat(file.exists()).isTrue()
        - assertThat(file.getName()).isEqualTo("orderList.vue")
    - name: shouldImplementSearchFilters
      assertions:
        - assertThat(component.hasSearchFilters()).isTrue()
        - >-
          assertThat(filters).containsKeys("orderNo", "assetName", "status",
          "dateRange")
    - name: shouldRenderDataTableColumns
      assertions:
        - assertThat(columns).hasSize(expectedColumnCount)
        - >-
          assertThat(columnNames).contains("orderNo", "assetName", "status",
          "createTime", "finishTime")
    - name: shouldShowActionButtonsBasedOnStatusAndRole
      assertions:
        - assertThat(actionButtons).isNotEmpty()
        - >-
          assertThat(visibleActions).containsOnlyElementsOf(expectedActionsForRole)
    - name: shouldDisplayStatusWithColoredBadges
      assertions:
        - assertThat(statusBadge.getColorClass()).isEqualTo(expectedColor)
        - assertThat(statusBadge.isVisible()).isTrue()
    - name: shouldSortByCreateTimeAndRequireFinishTime
      assertions:
        - 'assertThat(sortOptions).contains("createTime", "requireFinishTime")'
        - assertThat(defaultSort).isEqualTo("createTime")
  e2eScenarios:
    - name: user can view maintenance order list page
      steps:
        - navigate to /asset/maint/orderList
        - verify page title is displayed
        - verify data table is visible
    - name: user can filter orders using search filters
      steps:
        - navigate to order list page
        - enter order number in search field
        - select status from dropdown
        - click search button
        - verify filtered results are displayed
    - name: user can see action buttons based on role and status
      steps:
        - navigate to order list page
        - verify pending orders show approve/reject buttons for managers
        - verify completed orders show view-only button
        - verify different roles see different action sets
    - name: user can see status displayed with colored badges
      steps:
        - navigate to order list page
        - verify pending status shows yellow badge
        - verify completed status shows green badge
        - verify rejected status shows red badge
    - name: user can sort orders by create time and finish time
      steps:
        - navigate to order list page
        - click on create time column header
        - verify orders are sorted by create time
        - click on finish time column header
        - verify orders are sorted by finish time
  frameworkHint: surefire
---
# Create Maintenance Order List Page

## Context

Vue component for maintenance order list with workflow actions.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderList.vue`
2. Implement search filters:
3. Data table columns:
4. Action buttons based on status and role:
5. Status displayed with colored badges
6. Sort by create time and require finish time
## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: RuoYi list page with workflow actions
