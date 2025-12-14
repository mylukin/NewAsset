---
id: office.service
module: office
priority: 50
status: passing
version: 23
origin: manual
dependsOn:
  - office.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:26:41.817Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7e4d0d4a7c5333c242c94e1bb8804f4440f7ea06
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:25:43.239Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/asset/office/service/AssetOfficeServiceTest.java
    e2e: []
  unitTestCases:
    - name: should define IAssetOfficeService interface with required methods
      assertions:
        - assertNotNull(IAssetOfficeService.class)
        - assertTrue(IAssetOfficeService.class.isInterface())
    - name: should create AssetOfficeServiceImpl implementing IAssetOfficeService
      assertions:
        - 'assertInstanceOf(IAssetOfficeService.class, assetOfficeService)'
        - assertNotNull(assetOfficeService)
    - name: 'should implement CRUD methods - create, read, update, delete, list'
      assertions:
        - 'assertNotNull(service.getClass().getMethod("save", AssetOffice.class))'
        - 'assertNotNull(service.getClass().getMethod("getById", Long.class))'
        - >-
          assertNotNull(service.getClass().getMethod("update",
          AssetOffice.class))
        - 'assertNotNull(service.getClass().getMethod("deleteById", Long.class))'
        - >-
          assertNotNull(service.getClass().getMethod("list",
          AssetOfficeQuery.class))
    - name: should have @DataScope annotation for project filtering
      assertions:
        - assertTrue(method.isAnnotationPresent(DataScope.class))
        - 'assertEquals("project_id", dataScope.deptAlias())'
    - name: should have @Transactional annotation on write operations
      assertions:
        - assertTrue(saveMethod.isAnnotationPresent(Transactional.class))
        - assertTrue(updateMethod.isAnnotationPresent(Transactional.class))
        - assertTrue(deleteMethod.isAnnotationPresent(Transactional.class))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Office Asset Service

## Context

Service layer for office asset business logic.

## Acceptance Criteria

1. Create `IAssetOfficeService` interface
2. Create `AssetOfficeServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations
## Technical Notes

- Reference: PRD Section 6.6
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetOfficeServiceImpl`
