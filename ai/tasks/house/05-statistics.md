---
id: house.statistics
module: house
priority: 14
status: passing
version: 23
origin: manual
dependsOn:
  - house.service
supersedes: []
tags:
  - statistics
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:22:29.819Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e371c0a8ce69ecbe04527aca95fcf47e9ce827b7
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:20:09.449Z'
  generatedBy: claude
  forVersion: 18
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetHouseMapperStatisticsTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetHouseServiceStatisticsTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetHouseControllerStatisticsTest.java
    e2e: []
  unitTestCases:
    - name: selectHouseStatistics_shouldReturnStatisticsForGivenProject
      assertions:
        - assertNotNull(statistics)
        - 'assertEquals(expectedTotalCount, statistics.getTotalCount())'
        - 'assertEquals(expectedSelfUseCount, statistics.getSelfUseCount())'
    - name: >-
        selectHouseStatistics_shouldReturnAllProjectsStatisticsWhenProjectIdIsNull
      assertions:
        - assertNotNull(statistics)
        - assertTrue(statistics.getTotalCount() > 0)
    - name: getHouseStatistics_shouldDelegateToMapper
      assertions:
        - verify(assetHouseMapper).selectHouseStatistics(projectId)
        - assertNotNull(result)
    - name: getHouseStatistics_shouldCalculateRatesCorrectly
      assertions:
        - 'assertEquals(expectedSelfUseRate, result.getSelfUseRate())'
        - 'assertEquals(expectedRentRate, result.getRentRate())'
        - 'assertEquals(expectedIdleRate, result.getIdleRate())'
    - name: getStatistics_shouldReturnOkWithStatisticsVO
      assertions:
        - >-
          mockMvc.perform(get("/asset/house/statistics")).andExpect(status().isOk())
        - andExpect(jsonPath("$.data.totalCount").exists())
        - andExpect(jsonPath("$.data.selfUseCount").exists())
    - name: getStatistics_shouldSupportProjectIdFilter
      assertions:
        - >-
          mockMvc.perform(get("/asset/house/statistics").param("projectId",
          "1")).andExpect(status().isOk())
        - verify(assetHouseService).getHouseStatistics(1L)
    - name: statisticsVO_shouldContainAllRequiredFields
      assertions:
        - assertNotNull(vo.getTotalCount())
        - assertNotNull(vo.getSelfUseCount())
        - assertNotNull(vo.getSelfUseRate())
        - assertNotNull(vo.getRentCount())
        - assertNotNull(vo.getRentRate())
        - assertNotNull(vo.getIdleCount())
        - assertNotNull(vo.getIdleRate())
    - name: selectHouseStatistics_shouldUseEfficientSqlAggregation
      assertions:
        - >-
          // Verify SQL uses COUNT with CASE (check via query log or execution
          plan)
        - assertNotNull(statistics)
        - // Integration test to verify single query execution
  e2eScenarios: []
  frameworkHint: junit5-spring
---
# Implement House Asset Statistics

## Context

Provide statistics view for house assets including vacancy rates and usage distribution.

## Acceptance Criteria

1. Add method to `AssetHouseMapper`: `selectHouseStatistics(Long projectId)`
2. Add service method: `getHouseStatistics(Long projectId)`
3. Add controller endpoint: `GET /asset/house/statistics`
4. Return statistics VO containing:
5. Support project filter parameter
6. Use efficient SQL aggregation (COUNT with CASE)
## Technical Notes

- Reference: PRD Section 6.2.2 Point 4
- Pattern: Aggregation query with VO response
- SQL: GROUP BY with conditional counting
