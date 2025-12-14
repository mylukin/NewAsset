---
id: office.mapper
module: office
priority: 49
status: passing
version: 23
origin: manual
dependsOn:
  - office.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:25:23.040Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 716b72332fb809ece6db9e76661fe7f3131954ad
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:24:09.876Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/asset/mapper/AssetOfficeMapperTest.java
    e2e: []
  unitTestCases:
    - name: shouldDefineAssetOfficeMapperInterface
      assertions:
        - assertNotNull(mapper)
        - assertTrue(mapper instanceof AssetOfficeMapper)
    - name: shouldHaveValidXmlMapperConfiguration
      assertions:
        - >-
          assertNotNull(sqlSession.getConfiguration().getMappedStatement("com.example.asset.mapper.AssetOfficeMapper.selectList"))
    - name: shouldImplementRequiredMapperMethods
      assertions:
        - assertDoesNotThrow(() -> mapper.selectList(params))
        - assertDoesNotThrow(() -> mapper.selectById(id))
        - assertDoesNotThrow(() -> mapper.insert(entity))
        - assertDoesNotThrow(() -> mapper.update(entity))
        - assertDoesNotThrow(() -> mapper.deleteById(id))
    - name: shouldFilterByProjectId
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(r ->
          r.getProjectId().equals(projectId)))
    - name: shouldFilterByUseDeptId
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(r ->
          r.getUseDeptId().equals(useDeptId)))
    - name: shouldFilterByOfficeType
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(r ->
          r.getOfficeType().equals(officeType)))
    - name: shouldFilterByDutyUserId
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(r ->
          r.getDutyUserId().equals(dutyUserId)))
    - name: shouldFilterByStatus
      assertions:
        - 'assertEquals(expectedCount, result.size())'
        - >-
          assertTrue(result.stream().allMatch(r ->
          r.getStatus().equals(status)))
    - name: shouldApplyDataScopeFiltering
      assertions:
        - assertNotNull(result)
        - assertTrue(result.size() <= totalRecords)
        - verify(dataScopeHandler).applyScope(any())
    - name: shouldJoinWithBaseAssetTable
      assertions:
        - assertNotNull(result.get(0).getAssetCode())
        - assertNotNull(result.get(0).getAssetName())
        - 'assertEquals(expectedBaseAssetData, result.get(0).getBaseAsset())'
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Office Asset Mapper

## Context

MyBatis mapper for office asset CRUD operations.

## Acceptance Criteria

1. Create `AssetOfficeMapper` interface
2. Create XML mapper file
3. Implement methods:
4. Support filters: projectId, useDeptId, officeType, dutyUserId, status
5. Include data scope filtering
6. Join with base asset table
## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetOfficeMapper.xml`
