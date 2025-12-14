---
id: parking.service
module: parking
priority: 31
status: passing
version: 23
origin: manual
dependsOn:
  - parking.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:55:30.154Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7ad0f29cfc8feb5866d058c7cfa9943ce720a23c
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:54:34.952Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/asset/parking/service/AssetParkingServiceTest.java
    e2e: []
  unitTestCases:
    - name: should define IAssetParkingService interface with required methods
      assertions:
        - assertNotNull(IAssetParkingService.class)
        - assertTrue(IAssetParkingService.class.isInterface())
    - name: >-
        should create AssetParkingServiceImpl that implements
        IAssetParkingService
      assertions:
        - 'assertInstanceOf(IAssetParkingService.class, assetParkingService)'
        - assertNotNull(assetParkingService)
    - name: should implement CRUD methods - create parking asset
      assertions:
        - assertNotNull(result.getId())
        - 'assertEquals(input.getName(), result.getName())'
    - name: should implement CRUD methods - read parking asset by id
      assertions:
        - assertNotNull(result)
        - 'assertEquals(expectedId, result.getId())'
    - name: should implement CRUD methods - update parking asset
      assertions:
        - 'assertEquals(updatedName, result.getName())'
        - assertNotNull(result.getUpdateTime())
    - name: should implement CRUD methods - delete parking asset
      assertions:
        - assertDoesNotThrow(() -> service.deleteById(id))
        - assertNull(service.getById(id))
    - name: should implement CRUD methods - list parking assets with pagination
      assertions:
        - assertNotNull(page.getRecords())
        - 'assertEquals(expectedSize, page.getRecords().size())'
    - name: should apply @DataScope annotation for project filtering
      assertions:
        - assertTrue(hasDataScopeAnnotation(method))
        - 'verify(query).eq("projectId", currentProjectId)'
    - name: should use @Transactional for create operation
      assertions:
        - assertTrue(hasTransactionalAnnotation(createMethod))
        - verify(transactionManager).commit(any())
    - name: should use @Transactional for update operation
      assertions:
        - assertTrue(hasTransactionalAnnotation(updateMethod))
    - name: should use @Transactional for delete operation
      assertions:
        - assertTrue(hasTransactionalAnnotation(deleteMethod))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Parking Asset Service

## Context

Service layer for parking asset business logic.

## Acceptance Criteria

1. Create `IAssetParkingService` interface
2. Create `AssetParkingServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations
## Technical Notes

- Reference: PRD Section 6.4
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetParkingServiceImpl`
