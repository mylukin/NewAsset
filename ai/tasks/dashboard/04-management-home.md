---
id: dashboard.management-home
module: dashboard
priority: 72
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
  verifiedAt: '2025-12-14T14:02:37.082Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 0bfa3cb1175afc032eb3106c66324be2960b34be
  summary: 8/8 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T14:01:17.722Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/dashboard/ManagementDashboardTest.java
    e2e:
      - e2e/dashboard/management-home.spec.ts
  unitTestCases:
    - name: shouldCreateManagementVueComponentFile
      assertions:
        - >-
          assertThat(fileExists('src/views/asset/dashboard/management.vue')).isTrue()
    - name: shouldRenderMetricCards
      assertions:
        - assertThat(component.findAll('.metric-card')).isNotEmpty()
        - >-
          assertThat(metricCards).containsKeys('totalAssets', 'activeOrders',
          'pendingMaintenance')
    - name: shouldDisplayRecentOrdersTableWithTop10
      assertions:
        - assertThat(ordersTable.getRows()).hasSize(10)
        - >-
          assertThat(ordersTable.getColumns()).contains('orderNo', 'status',
          'createdAt')
    - name: shouldRenderProjectSelector
      assertions:
        - assertThat(component.find('.project-selector')).isNotNull()
        - assertThat(projectSelector.getOptions()).isNotEmpty()
    - name: shouldRefreshDataOnProjectChange
      assertions:
        - 'verify(dashboardService, times(2)).loadDashboardData(anyLong())'
        - assertThat(refreshedData.getProjectId()).isEqualTo(newProjectId)
    - name: shouldRenderEChartsPieChartForAssetDistribution
      assertions:
        - assertThat(component.find('.echarts-pie-chart')).isNotNull()
        - assertThat(chartData.getSeries()).hasSize(expectedCategoryCount)
    - name: shouldShowLoadingStatesForEachWidget
      assertions:
        - assertThat(metricCardsLoading).isTrue()
        - assertThat(ordersTableLoading).isTrue()
        - assertThat(pieChartLoading).isTrue()
    - name: shouldHaveResponsiveLayoutFor1920x1080
      assertions:
        - assertThat(component.getComputedStyle('display')).isEqualTo('grid')
        - assertThat(layoutBreakpoints).contains(1920)
  e2eScenarios:
    - name: user views management dashboard with metric cards
      steps:
        - navigate to /asset/dashboard/management
        - wait for loading to complete
        - verify metric cards are visible
        - verify each card displays a numeric value
    - name: user views recent orders table
      steps:
        - navigate to /asset/dashboard/management
        - wait for orders table to load
        - verify table has exactly 10 rows
        - 'verify table columns include order number, status, date'
    - name: user selects different project to filter data
      steps:
        - navigate to /asset/dashboard/management
        - click project selector dropdown
        - select a different project
        - verify all widgets refresh with new project data
    - name: user views asset distribution pie chart
      steps:
        - navigate to /asset/dashboard/management
        - wait for pie chart to render
        - verify pie chart is visible
        - hover over pie slice to see tooltip with category details
    - name: user sees loading states during data fetch
      steps:
        - navigate to /asset/dashboard/management
        - verify loading spinners appear for each widget
        - wait for loading to complete
        - verify data is displayed
    - name: dashboard displays correctly at 1920x1080 resolution
      steps:
        - set viewport to 1920x1080
        - navigate to /asset/dashboard/management
        - verify layout is properly arranged
        - verify no horizontal scrolling required
  frameworkHint: surefire/junit5
---
# Create Management Dashboard Home Page

## Context

Dashboard for management and operations roles showing key metrics and recent activity.

## Acceptance Criteria

1. Create `/src/views/asset/dashboard/management.vue`
2. Implement metric cards:
3. Implement recent orders table (Top 10)
4. Add project selector to filter all data
5. Auto-refresh on project change
6. Use ECharts for pie chart of asset distribution
7. Loading states for each widget
8. Responsive layout for 1920x1080
## Technical Notes

- Reference: PRD Section 7.1 Point 1
- Pattern: Dashboard with cards and charts
- Components: el-card, ECharts
