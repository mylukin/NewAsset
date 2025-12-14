---
id: maintenance.frontend-api
module: maintenance
priority: 63
status: passing
version: 23
origin: manual
dependsOn:
  - maintenance.order-controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:45:09.234Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 2e7ee549df30ccbdf7b6e404de8809706d8723f1
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:44:41.383Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/api/maintOrder/MaintOrderApiTest.java
    e2e: []
  unitTestCases:
    - name: should create maintOrder.js file at correct path
      assertions:
        - assertNotNull(maintOrderApi)
        - assertTrue(Files.exists(Paths.get('src/api/asset/maintOrder.js')))
    - name: should implement all required API functions
      assertions:
        - assertNotNull(maintOrderApi.listMaintOrder)
        - assertNotNull(maintOrderApi.getMaintOrder)
        - assertNotNull(maintOrderApi.addMaintOrder)
        - assertNotNull(maintOrderApi.updateMaintOrder)
        - assertNotNull(maintOrderApi.delMaintOrder)
    - name: should use RuoYi request utility for HTTP calls
      assertions:
        - assertTrue(fileContent.contains('import request from'))
        - 'assertTrue(fileContent.contains(''request({''))'
    - name: should export all API functions
      assertions:
        - assertTrue(fileContent.contains('export function listMaintOrder'))
        - assertTrue(fileContent.contains('export function getMaintOrder'))
        - assertTrue(fileContent.contains('export function addMaintOrder'))
        - assertTrue(fileContent.contains('export function updateMaintOrder'))
        - assertTrue(fileContent.contains('export function delMaintOrder'))
  e2eScenarios: []
  frameworkHint: surefire
---
# Create Maintenance Order Frontend API Module

## Context

Axios API module for maintenance order frontend.

## Acceptance Criteria

1. Create `/src/api/asset/maintOrder.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/maintOrder.js`
