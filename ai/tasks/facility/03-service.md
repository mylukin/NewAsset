---
id: facility.service
module: facility
priority: 22
status: passing
version: 24
origin: manual
dependsOn:
  - facility.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:38:48.349Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 765b837b819616ee1408a02670a22a79815c918f
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:37:51.963Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/service/impl/AssetFacilityServiceImplTest.java
    e2e: []
  unitTestCases:
    - name: should define IAssetFacilityService interface with all required methods
      assertions:
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("selectAssetFacilityList",
          AssetFacilityQuery.class))
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("selectAssetFacilityById",
          Long.class))
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("insertAssetFacility",
          AssetFacilityCreateDTO.class))
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("updateAssetFacility",
          AssetFacilityUpdateDTO.class))
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("deleteAssetFacilityById",
          Long.class))
        - >-
          assertNotNull(IAssetFacilityService.class.getMethod("deleteAssetFacilityByIds",
          Long[].class))
    - name: >-
        should create AssetFacilityServiceImpl that implements
        IAssetFacilityService
      assertions:
        - >-
          assertTrue(IAssetFacilityService.class.isAssignableFrom(AssetFacilityServiceImpl.class))
        - >-
          assertNotNull(AssetFacilityServiceImpl.class.getAnnotation(Service.class))
    - name: >-
        should implement selectAssetFacilityList method returning list of
        facilities
      assertions:
        - assertNotNull(result)
        - 'assertEquals(expectedSize, result.size())'
    - name: >-
        should implement selectAssetFacilityById method returning facility
        detail
      assertions:
        - assertNotNull(result)
        - 'assertEquals(expectedId, result.getId())'
    - name: should implement insertAssetFacility with generated asset code
      assertions:
        - assertNotNull(createdId)
        - >-
          verify(assetCodeGenerator).generateWithRetry(eq("facility"), any(),
          any(), eq(3))
    - name: should implement updateAssetFacility method
      assertions:
        - 'assertEquals(1, affectedRows)'
        - verify(assetMapper).updateById(any(Asset.class))
        - >-
          verify(assetFacilityMapper).updateAssetFacility(any(AssetFacility.class))
    - name: should implement deleteAssetFacilityById with soft delete
      assertions:
        - 'assertEquals(1, affectedRows)'
        - >-
          verify(assetMapper).updateById(argThat(asset ->
          "2".equals(asset.getDelFlag())))
    - name: should implement deleteAssetFacilityByIds for batch deletion
      assertions:
        - 'assertEquals(expectedCount, affectedRows)'
        - verify(assetFacilityMapper).deleteAssetFacilityByIds(ids)
    - name: >-
        should apply @DataScope annotation on selectAssetFacilityList for
        project filtering
      assertions:
        - >-
          assertNotNull(selectAssetFacilityListMethod.getAnnotation(DataScope.class))
        - 'assertEquals("a", dataScopeAnnotation.projectAlias())'
    - name: should apply @Transactional annotation on insertAssetFacility
      assertions:
        - assertNotNull(insertMethod.getAnnotation(Transactional.class))
        - >-
          assertEquals(Exception.class,
          transactionalAnnotation.rollbackFor()[0])
    - name: should apply @Transactional annotation on updateAssetFacility
      assertions:
        - assertNotNull(updateMethod.getAnnotation(Transactional.class))
    - name: should apply @Transactional annotation on delete methods
      assertions:
        - assertNotNull(deleteByIdMethod.getAnnotation(Transactional.class))
        - assertNotNull(deleteByIdsMethod.getAnnotation(Transactional.class))
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Facility Asset Service

## Context

Service layer for facility asset business logic with code generation integration.

## Acceptance Criteria

1. Create `IAssetFacilityService` interface
2. Create `AssetFacilityServiceImpl` implementation
3. Implement standard CRUD methods:
4. Integrate AssetCodeGenerator for code generation
5. Add @DataScope for project filtering
6. Use @Transactional for write operations
## Technical Notes

- Reference: PRD Section 6.3
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetFacilityServiceImpl`
