---
id: dashboard.service
module: dashboard
priority: 69
status: passing
version: 24
origin: manual
dependsOn:
  - house.statistics
  - parking.statistics
  - maintenance.order-crud-service
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:59:05.566Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 524af6af45f08c4a4e4b44af2757bdc594300854
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:56:40.507Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/dashboard/service/DashboardServiceImplTest.java
    e2e: []
  unitTestCases:
    - name: should define IDashboardService interface with required methods
      assertions:
        - assertNotNull(IDashboardService.class.getDeclaredMethods())
        - assertTrue(IDashboardService.class.isInterface())
    - name: should create DashboardServiceImpl implementing IDashboardService
      assertions:
        - 'assertInstanceOf(IDashboardService.class, dashboardService)'
        - assertNotNull(dashboardService)
    - name: should implement management dashboard methods returning aggregated KPIs
      assertions:
        - assertNotNull(dashboardService.getManagementDashboard())
        - 'assertEquals(expectedTotalAssetValue, result.getTotalAssetValue())'
        - assertNotNull(result.getAssetStatusDistribution())
    - name: should implement ops dashboard methods returning operational metrics
      assertions:
        - assertNotNull(dashboardService.getOpsDashboard())
        - assertNotNull(result.getPendingMaintenanceOrders())
        - assertNotNull(result.getRecentActivities())
    - name: >-
        should implement asset manager dashboard methods returning portfolio
        data
      assertions:
        - assertNotNull(dashboardService.getAssetManagerDashboard())
        - assertNotNull(result.getManagedAssets())
        - assertNotNull(result.getUpcomingMaintenanceSchedule())
    - name: should apply data scope filtering based on user permissions
      assertions:
        - 'verify(dataScopeFilter).applyScope(any(), eq(currentUser))'
        - 'assertEquals(filteredAssetCount, result.getAssets().size())'
        - >-
          assertFalse(result.getAssets().stream().anyMatch(a ->
          !allowedDepartments.contains(a.getDepartmentId())))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Dashboard Service

## Context

Unified service aggregating statistics from all asset modules for dashboard display.

## Acceptance Criteria

1. Create `IDashboardService` interface
2. Create `DashboardServiceImpl` implementation
3. Implement methods for management dashboard:
4. Implement methods for ops dashboard:
5. Implement methods for asset manager dashboard:
6. Apply data scope filtering
## Technical Notes

- Reference: PRD Section 7
- Pattern: Aggregation service calling other services
- Location: `com.ruoyi.asset.service.impl.DashboardServiceImpl`
