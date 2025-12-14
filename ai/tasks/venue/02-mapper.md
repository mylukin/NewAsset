---
id: venue.mapper
module: venue
priority: 40
status: passing
version: 23
origin: manual
dependsOn:
  - venue.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:11:15.147Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 0107a4c5363cf438980c9bbc95ff1598dd3e60ac
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:09:43.199Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/mapper/AssetVenueMapperTest.java
    e2e: []
  unitTestCases:
    - name: should define AssetVenueMapper interface with required methods
      assertions:
        - assertNotNull(AssetVenueMapper.class)
        - assertTrue(AssetVenueMapper.class.isInterface())
    - name: should have XML mapper file configured correctly
      assertions:
        - assertNotNull(sqlSession.getMapper(AssetVenueMapper.class))
        - >-
          assertNotNull(sqlSession.getConfiguration().getMappedStatement("com.newasset.mapper.AssetVenueMapper.selectById"))
    - name: should implement selectById method
      assertions:
        - assertNotNull(mapper.selectById(1L))
        - 'assertEquals(expectedVenue.getId(), result.getId())'
    - name: should implement selectList method with pagination
      assertions:
        - assertNotNull(mapper.selectList(queryParams))
        - assertTrue(result.size() <= pageSize)
    - name: should filter by projectId
      assertions:
        - 'assertEquals(projectId, result.get(0).getProjectId())'
        - >-
          assertTrue(result.stream().allMatch(v ->
          v.getProjectId().equals(projectId)))
    - name: should filter by venueType
      assertions:
        - 'assertEquals(venueType, result.get(0).getVenueType())'
        - >-
          assertTrue(result.stream().allMatch(v ->
          v.getVenueType().equals(venueType)))
    - name: should filter by currentVenueStatus
      assertions:
        - >-
          assertEquals(currentVenueStatus,
          result.get(0).getCurrentVenueStatus())
        - >-
          assertTrue(result.stream().allMatch(v ->
          v.getCurrentVenueStatus().equals(currentVenueStatus)))
    - name: should filter by status
      assertions:
        - 'assertEquals(status, result.get(0).getStatus())'
        - >-
          assertTrue(result.stream().allMatch(v ->
          v.getStatus().equals(status)))
    - name: should apply data scope filtering
      assertions:
        - assertNotNull(result)
        - >-
          assertTrue(result.stream().allMatch(v ->
          userDataScope.contains(v.getProjectId())))
    - name: should join with base asset table
      assertions:
        - assertNotNull(result.get(0).getAssetCode())
        - assertNotNull(result.get(0).getAssetName())
        - 'assertEquals(expectedAssetCode, result.get(0).getAssetCode())'
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Venue Asset Mapper

## Context

MyBatis mapper for venue asset CRUD operations.

## Acceptance Criteria

1. Create `AssetVenueMapper` interface
2. Create XML mapper file
3. Implement methods:
4. Support filters: projectId, venueType, currentVenueStatus, status
5. Include data scope filtering
6. Join with base asset table
## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetVenueMapper.xml`
