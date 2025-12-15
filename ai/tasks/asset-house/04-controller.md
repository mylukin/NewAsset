---
id: asset-house.controller
module: asset-house
priority: 204
status: passing
version: 6
origin: spec-workflow
dependsOn:
  - asset-house.service
supersedes: []
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: tests/asset-house/**/*.test.*
verification:
  verifiedAt: '2025-12-15T13:38:07.499Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e891425567f4c342f198c866888a6e9a21b1caa5
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:33:25.111Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-house/controller.test.ts
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetHouseController class in com.ruoyi.asset.controller
        package
      assertions:
        - expect(AssetHouseController).toBeDefined()
        - expect(AssetHouseController.name).toBe('AssetHouseController')
    - name: should implement required REST API endpoints
      assertions:
        - expect(assetHouseController.create).toBeDefined()
        - expect(assetHouseController.update).toBeDefined()
        - expect(assetHouseController.list).toBeDefined()
        - expect(assetHouseController.get).toBeDefined()
        - expect(assetHouseController.remove).toBeDefined()
    - name: should have @Log annotation for audit logging
      assertions:
        - expect(AssetHouseController).toHaveProperty('_@Log')
        - expect(hasLogAnnotation).toBe(true)
    - name: should have @DataScope annotation for project-based data permission
      assertions:
        - expect(AssetHouseController).toHaveProperty('_@DataScope')
        - expect(hasDataScopeAnnotation).toBe(true)
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement House Asset REST API

## Context

REST API for house asset CRUD operations. Supports list page and detail drawer.

## Acceptance Criteria

1. Create `AssetHouseController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add @Log annotation for audit logging
4. Apply @DataScope for project-based data permission
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (RESTful patterns)
- UX: ai/tasks/spec/UX-DESIGN.md (Asset List screen)
