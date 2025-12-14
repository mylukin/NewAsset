---
id: house.service
module: house
priority: 12
status: passing
version: 23
origin: manual
dependsOn:
  - house.mapper
  - core.code-generator
  - core.state-machine
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:18:06.110Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: de13b3b45565dde8875aca882e012343186ea5de
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:16:24.058Z'
  generatedBy: claude
  forVersion: 16
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/house/service/AssetHouseServiceTest.java
    e2e: []
  unitTestCases:
    - name: should define IAssetHouseService interface with required methods
      assertions:
        - assertNotNull(IAssetHouseService.class)
        - assertTrue(IAssetHouseService.class.isInterface())
    - name: should create AssetHouseServiceImpl implementing IAssetHouseService
      assertions:
        - >-
          assertTrue(IAssetHouseService.class.isAssignableFrom(AssetHouseServiceImpl.class))
        - assertNotNull(new AssetHouseServiceImpl(...))
    - name: should implement CRUD and query methods
      assertions:
        - assertNotNull(service.insert(house))
        - assertNotNull(service.update(house))
        - assertNotNull(service.delete(id))
        - assertNotNull(service.selectById(id))
        - assertNotNull(service.selectList(query))
    - name: should integrate AssetCodeGenerator for code generation on insert
      assertions:
        - verify(assetCodeGenerator).generateCode(any())
        - assertNotNull(result.getAssetCode())
        - assertTrue(result.getAssetCode().startsWith("HOUSE"))
    - name: should integrate AssetStatusService for status changes
      assertions:
        - 'verify(assetStatusService).changeStatus(any(), any())'
        - 'assertEquals(expectedStatus, result.getStatus())'
    - name: should have DataScope annotation for project-based filtering
      assertions:
        - assertTrue(method.isAnnotationPresent(DataScope.class))
        - assertNotNull(service.getClass().getAnnotation(DataScope.class))
    - name: should use Transactional annotation for insert and update operations
      assertions:
        - assertTrue(insertMethod.isAnnotationPresent(Transactional.class))
        - assertTrue(updateMethod.isAnnotationPresent(Transactional.class))
  e2eScenarios: []
  frameworkHint: junit5-mockito
---
# Create House Asset Service

## Context

Service layer for house asset business logic, coordinating code generation, status management, and CRUD operations.

## Acceptance Criteria

1. Create `IAssetHouseService` interface
2. Create `AssetHouseServiceImpl` implementation
3. Implement methods:
4. Integrate `AssetCodeGenerator` for code generation on insert
5. Integrate `AssetStatusService` for status changes
6. Add @DataScope annotation for project-based filtering
7. Use @Transactional for insert/update operations
## Technical Notes

- Reference: PRD Section 6.2.2 (copy feature)
- Pattern: Service + Mapper pattern with domain service integration
- Location: `com.ruoyi.asset.service.impl.AssetHouseServiceImpl`
