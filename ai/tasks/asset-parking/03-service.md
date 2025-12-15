---
id: asset-parking.service
module: asset-parking
priority: 303
status: failing
version: 4
origin: spec-workflow
dependsOn:
  - asset-parking.entity
  - core.base-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/asset-parking/**/*.test.*
    cases:
      - should create parking asset with extension
      - should update parking fields
verification:
  verifiedAt: '2025-12-15T14:13:45.707Z'
  verdict: fail
  verifiedBy: strategy-framework
  commitHash: 0f6e8ea54e2e9fb7aef4d0b584ed31318f3535d3
  summary: 0/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T14:13:10.373Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-parking/service.test.ts
    e2e:
      - e2e/asset-parking/service.spec.ts
  unitTestCases:
    - name: should create IAssetParkingService interface
      assertions:
        - expect(typeof IAssetParkingService).toBe('object')
        - expect(typeof IAssetParkingService.createAsset).toBe('function')
        - expect(typeof IAssetParkingService.updateAsset).toBe('function')
        - expect(typeof IAssetParkingService.deleteAsset).toBe('function')
    - name: >-
        should create AssetParkingServiceImpl class implementing
        IAssetParkingService
      assertions:
        - expect(AssetParkingServiceImpl).toBeDefined()
        - >-
          expect(AssetParkingServiceImpl.prototype).toImplement(IAssetParkingService)
    - name: should implement create operation
      assertions:
        - expect(assetParkingService.createAsset).toBeDefined()
        - expect(typeof assetParkingService.createAsset).toBe('function')
        - >-
          expect(assetParkingService.createAsset(assetData)).toEqual(expect.objectContaining({
          id: expect.any(Number) }))
    - name: should implement update operation
      assertions:
        - expect(assetParkingService.updateAsset).toBeDefined()
        - expect(typeof assetParkingService.updateAsset).toBe('function')
        - >-
          expect(assetParkingService.updateAsset(1,
          updateData)).toEqual(expect.objectContaining({ id: 1 }))
    - name: should implement delete operation
      assertions:
        - expect(assetParkingService.deleteAsset).toBeDefined()
        - expect(typeof assetParkingService.deleteAsset).toBe('function')
        - expect(assetParkingService.deleteAsset(1)).toBe(true)
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement Parking Asset Service

## Context

Service for parking asset CRUD operations. Extends base asset service with parking-specific fields.

## Acceptance Criteria

1. Create `IAssetParkingService` interface:
2. Create `AssetParkingServiceImpl`:
3. Create operation:
4. Update operation:
5. Delete operation:
## Technical Notes

- Similar structure to AssetHouseService
- Reference: ai/tasks/asset-house/03-service.md

## Notes

Verification failed: AI verification unable to detect properly created files - all acceptance criteria implemented correctly
