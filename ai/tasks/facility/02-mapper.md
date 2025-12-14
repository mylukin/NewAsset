---
id: facility.mapper
module: facility
priority: 21
status: passing
version: 24
origin: manual
dependsOn:
  - facility.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:37:01.528Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6f2db46f56a560a59b8d14da8b7e390b894b6b07
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:35:51.237Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetFacilityMapperTest.java
    e2e: []
  unitTestCases:
    - name: should define AssetFacilityMapper interface with @Mapper annotation
      assertions:
        - assertNotNull(AssetFacilityMapper.class.getAnnotation(Mapper.class))
    - name: should select facility asset list with pagination support
      assertions:
        - assertNotNull(result)
        - assertTrue(result instanceof List)
        - 'assertEquals(expectedSize, result.size())'
    - name: should select facility asset detail by ID with joined base asset fields
      assertions:
        - assertNotNull(detail)
        - 'assertEquals(expectedId, detail.getId())'
        - assertNotNull(detail.getAssetCode())
        - assertNotNull(detail.getFacilityType())
    - name: should insert facility asset extension record
      assertions:
        - 'assertEquals(1, insertedRows)'
        - assertNotNull(facility.getAssetId())
    - name: should update facility asset extension record
      assertions:
        - 'assertEquals(1, updatedRows)'
    - name: should delete facility asset extensions by IDs
      assertions:
        - 'assertEquals(expectedDeleteCount, deletedRows)'
    - name: should filter by projectId
      assertions:
        - >-
          assertTrue(result.stream().allMatch(f ->
          f.getProjectId().equals(expectedProjectId)))
    - name: should filter by building
      assertions:
        - >-
          assertTrue(result.stream().allMatch(f ->
          f.getBuilding().contains(expectedBuilding)))
    - name: should filter by floor
      assertions:
        - >-
          assertTrue(result.stream().allMatch(f ->
          f.getFloor().equals(expectedFloor)))
    - name: should filter by facilityType
      assertions:
        - >-
          assertTrue(result.stream().allMatch(f ->
          f.getFacilityType().equals(expectedFacilityType)))
    - name: should filter by status
      assertions:
        - >-
          assertTrue(result.stream().allMatch(f ->
          f.getStatus().equals(expectedStatus)))
    - name: should apply data scope filtering via dataScope parameter
      assertions:
        - assertNotNull(query.getDataScope())
        - assertTrue(result.size() <= unfilterredResult.size())
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Facility Asset Mapper

## Context

MyBatis mapper for facility asset CRUD operations with base asset table join.

## Acceptance Criteria

1. Create `AssetFacilityMapper` interface
2. Create corresponding XML mapper file
3. Implement methods:
4. Support filters: projectId, building, floor, facilityType, status
5. Include data scope filtering
## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper with join
- Location: `resources/mapper/asset/AssetFacilityMapper.xml`
