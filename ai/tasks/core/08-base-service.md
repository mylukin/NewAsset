---
id: core.base-service
module: core
priority: 108
status: failing
version: 3
origin: spec-workflow
dependsOn:
  - core.asset-entity
  - core.code-generator
  - core.status-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
tddGuidance:
  generatedAt: '2025-12-15T15:29:18.003Z'
  generatedBy: claude
  forVersion: 2
  suggestedTestFiles:
    unit:
      - tests/core/base-service.test.ts
    e2e:
      - e2e/core/base-service.spec.ts
  unitTestCases:
    - name: should create IAssetService interface with required methods
      assertions:
        - expect(IAssetService.prototype.create).toBeDefined()
        - expect(IAssetService.prototype.delete).toBeDefined()
        - expect(IAssetService.prototype.list).toBeDefined()
        - expect(typeof IAssetService.prototype.create).toBe('function')
        - expect(typeof IAssetService.prototype.delete).toBe('function')
        - expect(typeof IAssetService.prototype.list).toBe('function')
    - name: should create AssetServiceImpl implementing IAssetService
      assertions:
        - expect(AssetServiceImpl.prototype).toBeInstanceOf(Object)
        - expect(new AssetServiceImpl()).toHaveProperty('create')
        - expect(new AssetServiceImpl()).toHaveProperty('delete')
        - expect(new AssetServiceImpl()).toHaveProperty('list')
    - name: should implement create operation for assets
      assertions:
        - expect(await service.create(assetData)).toBeDefined()
        - expect(await service.create(assetData)).toHaveProperty('id')
        - expect(await service.create(assetData).name).toBe(assetData.name)
    - name: should implement delete operation for assets
      assertions:
        - expect(await service.delete(assetId)).toBe(true)
        - expect(await service.delete(invalidId)).toBe(false)
    - name: should implement list operation with filters
      assertions:
        - expect(await service.list(filters)).toHaveLength(2)
        - 'expect(await service.list(filters)[0]).toHaveProperty(''id'')'
        - >-
          expect(await
          service.list({})).toEqual(expect.arrayContaining([expect.objectContaining({id:
          expect.any(Number)})]))
    - name: should enforce data permission restrictions
      assertions:
        - expect(() => service.list()).toThrow()
        - >-
          expect(await
          service.create(invalidPermissionData)).toHaveProperty('error')
        - expect(await service.delete(restrictedAssetId)).toBe(false)
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement Base Asset Service

## Context

Base service with common asset CRUD operations. Extended by type-specific services (house, parking, etc.).

## Acceptance Criteria

1. Create `IAssetService` interface in `com.ruoyi.asset.service`:
2. Create `AssetServiceImpl` implementing IAssetService:
3. Create operation:
4. Delete operation:
5. List operation with filters:
6. Data permission:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Delete rule: block if open WO)
- Extended by house, parking, facility, venue, office services

## Notes

Verification failed: Implementation complete - IAssetService interface, AssetServiceImpl with CRUD, DTOs, and pagination
