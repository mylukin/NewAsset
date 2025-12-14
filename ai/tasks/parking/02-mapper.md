---
id: parking.mapper
module: parking
priority: 30
status: passing
version: 24
origin: manual
dependsOn:
  - parking.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:54:11.489Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: c17ed6a922e2666ed5b205fdb8182393489c6fd5
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:53:03.425Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/mapper/AssetParkingMapperTest.java
    e2e: []
  unitTestCases:
    - name: shouldDefineAssetParkingMapperInterface
      assertions:
        - assertNotNull(AssetParkingMapper.class)
        - assertTrue(AssetParkingMapper.class.isInterface())
    - name: shouldMapXmlFileToMapperInterface
      assertions:
        - assertNotNull(assetParkingMapper)
        - assertTrue(assetParkingMapper instanceof AssetParkingMapper)
    - name: shouldImplementCrudMethods
      assertions:
        - assertNotNull(assetParkingMapper.selectById(1L))
        - assertNotNull(assetParkingMapper.selectList(queryParams))
        - 'assertEquals(1, assetParkingMapper.insert(parking))'
        - 'assertEquals(1, assetParkingMapper.update(parking))'
        - 'assertEquals(1, assetParkingMapper.deleteById(1L))'
    - name: shouldFilterByProjectId
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(p ->
          p.getProjectId().equals(projectId)))
    - name: shouldFilterByParkingZone
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(p ->
          p.getParkingZone().equals(zone)))
    - name: shouldFilterByParkingNo
      assertions:
        - 'assertEquals(1, result.size())'
        - 'assertEquals(parkingNo, result.get(0).getParkingNo())'
    - name: shouldFilterByParkingType
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(p ->
          p.getParkingType().equals(type)))
    - name: shouldFilterByStatus
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(p ->
          p.getStatus().equals(status)))
    - name: shouldApplyDataScopeFiltering
      assertions:
        - assertNotNull(result)
        - assertTrue(result.size() <= totalCount)
        - verify(dataScopeHandler).applyScope(any())
    - name: shouldJoinWithBaseAssetTable
      assertions:
        - assertNotNull(result.getAssetName())
        - assertNotNull(result.getAssetCode())
        - 'assertEquals(expectedAssetId, result.getAssetId())'
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Parking Asset Mapper

## Context

MyBatis mapper for parking asset CRUD operations.

## Acceptance Criteria

1. Create `AssetParkingMapper` interface
2. Create XML mapper file
3. Implement methods:
4. Support filters: projectId, parkingZone, parkingNo, parkingType, status
5. Include data scope filtering
6. Join with base asset table
## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetParkingMapper.xml`
