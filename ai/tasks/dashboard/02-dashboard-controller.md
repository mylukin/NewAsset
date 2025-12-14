---
id: dashboard.controller
module: dashboard
priority: 70
status: passing
version: 24
origin: manual
dependsOn:
  - dashboard.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
verification:
  verifiedAt: '2025-12-14T14:00:07.702Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 15c2465eacc23f5169fb87e26ee4aa879d35e6e8
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:59:28.023Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/dashboard/DashboardControllerTest.java
    e2e: []
  unitTestCases:
    - name: testDashboardControllerExists
      assertions:
        - assertNotNull(dashboardController)
        - assertTrue(dashboardController instanceof DashboardController)
    - name: testGetAssetSummaryEndpoint
      assertions:
        - 'assertEquals(HttpStatus.OK, response.getStatusCode())'
        - assertNotNull(response.getBody())
    - name: testGetMaintenanceOverviewEndpoint
      assertions:
        - 'assertEquals(HttpStatus.OK, response.getStatusCode())'
        - assertNotNull(response.getBody().getScheduledCount())
    - name: testGetRecentActivitiesEndpoint
      assertions:
        - 'assertEquals(HttpStatus.OK, response.getStatusCode())'
        - assertTrue(response.getBody() instanceof List)
    - name: testEndpointsWithProjectIdFilter
      assertions:
        - 'assertEquals(projectId, response.getBody().getProjectId())'
        - >-
          assertTrue(response.getBody().getAssets().stream().allMatch(a ->
          a.getProjectId().equals(projectId)))
    - name: testPermissionCheckOnEndpoints
      assertions:
        - >-
          assertEquals(HttpStatus.FORBIDDEN,
          unauthorizedResponse.getStatusCode())
        - 'assertEquals(HttpStatus.OK, authorizedResponse.getStatusCode())'
    - name: testOptimizedResponseForDashboardWidgets
      assertions:
        - assertTrue(response.getBody().getAssets().size() <= MAX_WIDGET_ITEMS)
        - assertNotNull(response.getBody().getSummary())
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Dashboard Controller

## Context

RESTful API endpoints for dashboard data retrieval.

## Acceptance Criteria

1. Create `DashboardController`
2. Implement endpoints:
3. All endpoints support projectId filter parameter
4. Add appropriate permission checks
5. Return optimized response for dashboard widgets
## Technical Notes

- Reference: PRD Section 7.1
- Pattern: RuoYi BaseController
- Permissions: Varies by role-based dashboard
