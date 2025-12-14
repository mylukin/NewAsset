---
id: dashboard.frontend-api
module: dashboard
priority: 71
status: passing
version: 24
origin: manual
dependsOn:
  - dashboard.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/dashboard/**/*.test.*
verification:
  verifiedAt: '2025-12-14T14:00:54.436Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: cb48a1cc58041c80b21345db8a7f7b29b8d7d8c9
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T14:00:27.318Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/api/dashboard/DashboardApiTest.java
    e2e: []
  unitTestCases:
    - name: should create dashboard.js file in correct location
      assertions:
        - assertThat(file).exists()
        - assertThat(file.getPath()).contains('/src/api/asset/dashboard.js')
    - name: should implement required API functions
      assertions:
        - assertThat(dashboardApi).hasMethod('getAssetOverview')
        - assertThat(dashboardApi).hasMethod('getAssetStatusDistribution')
        - assertThat(dashboardApi).hasMethod('getMaintenanceStats')
    - name: should use RuoYi request utility for HTTP calls
      assertions:
        - assertThat(fileContent).contains('import request from')
        - 'assertThat(fileContent).contains(''request({'')'
    - name: should export all API functions
      assertions:
        - assertThat(fileContent).contains('export function')
        - >-
          assertThat(exports).containsAll(['getAssetOverview',
          'getAssetStatusDistribution', 'getMaintenanceStats'])
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Dashboard Frontend API Module

## Context

Axios API module for dashboard data retrieval.

## Acceptance Criteria

1. Create `/src/api/asset/dashboard.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/dashboard.js`
