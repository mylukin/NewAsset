---
id: asset-parking.controller
module: asset-parking
priority: 304
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-parking.service
supersedes: []
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: tests/asset-parking/**/*.test.*
tddGuidance:
  generatedAt: '2025-12-15T14:27:16.700Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-parking/controller.test.ts
    e2e: []
  unitTestCases:
    - name: should instantiate AssetParkingController with required dependencies
      assertions:
        - expect(AssetParkingController).toBeDefined()
        - expect(controller).toHaveProperty('parkingService')
    - name: should implement parking asset REST endpoints with correct routing
      assertions:
        - expect(parkingRoutes).toHaveLength(4)
        - >-
          expect(parkingRoutes).toContainEqual(expect.objectContaining({ method:
          'GET', path: '/parking/list' }))
        - >-
          expect(parkingRoutes).toContainEqual(expect.objectContaining({ method:
          'POST', path: '/parking' }))
    - name: should add @Log annotation for audit logging on all endpoints
      assertions:
        - 'expect(controllerClass).toHaveProperty(''_logAnnotated'', true)'
        - 'expect(methodMetadata).toEqual(expect.arrayContaining([''log'']))'
    - name: should apply @DataScope annotation for project-based permission control
      assertions:
        - 'expect(controllerClass).toHaveProperty(''_dataScoped'', true)'
        - expect(hasDataScopeAnnotation).toBe(true)
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement Parking Asset REST API

## Context

REST API for parking asset CRUD operations.

## Acceptance Criteria

1. Create `AssetParkingController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add @Log annotation for audit logging
4. Apply @DataScope for project-based permission
## Technical Notes

- Similar structure to AssetHouseController
- Reference: ai/tasks/asset-house/04-controller.md
