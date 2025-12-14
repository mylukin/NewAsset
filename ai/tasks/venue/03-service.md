---
id: venue.service
module: venue
priority: 41
status: passing
version: 23
origin: manual
dependsOn:
  - venue.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:12:24.472Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: c0bd054e7bb6917a91b55f498ddc503496e60eb8
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:11:38.688Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/asset/venue/service/AssetVenueServiceTest.java
    e2e: []
  unitTestCases:
    - name: should have IAssetVenueService interface defined with required methods
      assertions:
        - assertNotNull(IAssetVenueService.class)
        - assertTrue(IAssetVenueService.class.isInterface())
    - name: should create AssetVenueServiceImpl that implements IAssetVenueService
      assertions:
        - assertNotNull(assetVenueServiceImpl)
        - 'assertInstanceOf(IAssetVenueService.class, assetVenueServiceImpl)'
    - name: should implement CRUD methods list/getInfo/add/edit/remove
      assertions:
        - assertDoesNotThrow(() -> service.list(query))
        - assertDoesNotThrow(() -> service.getInfo(id))
        - assertDoesNotThrow(() -> service.add(bo))
        - assertDoesNotThrow(() -> service.edit(bo))
        - assertDoesNotThrow(() -> service.remove(ids))
    - name: should apply @DataScope annotation for project filtering on list method
      assertions:
        - assertNotNull(listMethod.getAnnotation(DataScope.class))
        - 'assertEquals("project_id", dataScopeAnnotation.value())'
    - name: should apply @Transactional annotation on add/edit/remove methods
      assertions:
        - assertNotNull(addMethod.getAnnotation(Transactional.class))
        - assertNotNull(editMethod.getAnnotation(Transactional.class))
        - assertNotNull(removeMethod.getAnnotation(Transactional.class))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Venue Asset Service

## Context

Service layer for venue asset business logic.

## Acceptance Criteria

1. Create `IAssetVenueService` interface
2. Create `AssetVenueServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations
## Technical Notes

- Reference: PRD Section 6.5
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetVenueServiceImpl`
