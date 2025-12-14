---
id: house.mapper
module: house
priority: 11
status: passing
version: 19
origin: manual
dependsOn:
  - house.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:16:01.580Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 5568bac30ffdda01ab9d0936b8f12874ea6a2eef
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:13:59.486Z'
  generatedBy: claude
  forVersion: 15
  suggestedTestFiles:
    unit:
      - src/test/java/com/ruoyi/asset/mapper/AssetHouseMapperTest.java
    e2e: []
  unitTestCases:
    - name: should define AssetHouseMapper interface in correct package
      assertions:
        - assertNotNull(AssetHouseMapper.class)
        - >-
          assertEquals("com.ruoyi.asset.mapper",
          AssetHouseMapper.class.getPackage().getName())
    - name: should have corresponding XML mapper file with correct namespace
      assertions:
        - assertTrue(xmlMapperExists("AssetHouseMapper.xml"))
        - >-
          assertEquals("com.ruoyi.asset.mapper.AssetHouseMapper",
          getXmlNamespace())
    - name: should implement selectAssetHouseList method
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("selectAssetHouseList",
          AssetHouse.class))
        - 'assertInstanceOf(List.class, mapper.selectAssetHouseList(query))'
    - name: should implement selectAssetHouseById method
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("selectAssetHouseById",
          Long.class))
        - assertNotNull(mapper.selectAssetHouseById(1L))
    - name: should implement insertAssetHouse method
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("insertAssetHouse",
          AssetHouse.class))
        - 'assertEquals(1, mapper.insertAssetHouse(newHouse))'
    - name: should implement updateAssetHouse method
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("updateAssetHouse",
          AssetHouse.class))
        - 'assertEquals(1, mapper.updateAssetHouse(existingHouse))'
    - name: should implement deleteAssetHouseById method
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("deleteAssetHouseById",
          Long.class))
        - 'assertEquals(1, mapper.deleteAssetHouseById(1L))'
    - name: should implement deleteAssetHouseByIds method for batch delete
      assertions:
        - >-
          assertNotNull(AssetHouseMapper.class.getMethod("deleteAssetHouseByIds",
          Long[].class))
        - 'assertEquals(3, mapper.deleteAssetHouseByIds(new Long[]{1L, 2L, 3L}))'
    - name: should support data scope filtering via params.dataScope
      assertions:
        - 'assertTrue(xmlContains("${params.dataScope}"))'
        - assertNotNull(mapper.selectAssetHouseList(queryWithDataScope))
    - name: should filter by projectId
      assertions:
        - >-
          assertEquals(2,
          mapper.selectAssetHouseList(queryWithProjectId).size())
        - 'assertTrue(allResultsMatchProjectId(results, expectedProjectId))'
    - name: should filter by building
      assertions:
        - 'assertEquals(3, mapper.selectAssetHouseList(queryWithBuilding).size())'
        - 'assertTrue(allResultsMatchBuilding(results, expectedBuilding))'
    - name: should filter by floor
      assertions:
        - 'assertEquals(4, mapper.selectAssetHouseList(queryWithFloor).size())'
        - 'assertTrue(allResultsMatchFloor(results, expectedFloor))'
    - name: should filter by status
      assertions:
        - 'assertEquals(5, mapper.selectAssetHouseList(queryWithStatus).size())'
        - 'assertTrue(allResultsMatchStatus(results, expectedStatus))'
    - name: should filter by houseType
      assertions:
        - >-
          assertEquals(2,
          mapper.selectAssetHouseList(queryWithHouseType).size())
        - 'assertTrue(allResultsMatchHouseType(results, expectedHouseType))'
    - name: should filter by currentUsage
      assertions:
        - >-
          assertEquals(3,
          mapper.selectAssetHouseList(queryWithCurrentUsage).size())
        - 'assertTrue(allResultsMatchCurrentUsage(results, expectedUsage))'
    - name: should filter by areaRange (min and max)
      assertions:
        - >-
          assertEquals(4,
          mapper.selectAssetHouseList(queryWithAreaRange).size())
        - 'assertTrue(allResultsWithinAreaRange(results, minArea, maxArea))'
    - name: should join query to include asset base fields
      assertions:
        - assertNotNull(result.getAssetName())
        - assertNotNull(result.getAssetCode())
        - assertNotNull(result.getProjectName())
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create House Asset Mapper

## Context

MyBatis mapper for house asset CRUD operations, joining base asset table with house extension table.

## Acceptance Criteria

1. Create `AssetHouseMapper` interface in `com.ruoyi.asset.mapper`
2. Create corresponding XML mapper file
3. Implement methods:
4. Support data scope filtering via `${params.dataScope}`
5. Include filters: projectId, building, floor, status, houseType, currentUsage, areaRange
6. Join query to include asset base fields
## Technical Notes

- Reference: TECH.md Section 6.4
- Pattern: MyBatis XML mapper with join queries
- Location: `resources/mapper/asset/AssetHouseMapper.xml`
