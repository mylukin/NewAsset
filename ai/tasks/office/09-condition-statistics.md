---
id: office.condition-statistics
module: office
priority: 56
status: passing
version: 23
origin: manual
dependsOn:
  - office.service
supersedes: []
tags:
  - statistics
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:35:15.199Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 62a6dd17997b9d0add4a5a6eccf815d75476317a
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:31:47.761Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/yxboot/modules/asset/office/service/OfficeAssetServiceConditionStatsTest.java
      - >-
        src/test/java/com/yxboot/modules/asset/office/controller/OfficeAssetControllerConditionStatsTest.java
    e2e: []
  unitTestCases:
    - name: >-
        selectOfficeConditionStats should return condition statistics for given
        projectId and deptId
      assertions:
        - assertNotNull(result)
        - 'assertEquals(expectedCount, result.size())'
        - 'assertEquals(expectedCondition, result.get(0).getCondition())'
    - name: >-
        getOfficeConditionStatistics service should aggregate condition counts
        correctly
      assertions:
        - assertNotNull(statistics)
        - 'assertEquals(expectedTotal, statistics.getTotal())'
        - assertTrue(statistics.getConditions().containsKey("GOOD"))
    - name: >-
        GET /asset/office/statistics/condition should return 200 with statistics
        VO
      assertions:
        - >-
          mockMvc.perform(get("/asset/office/statistics/condition")).andExpect(status().isOk())
        - andExpect(jsonPath("$.data").exists())
        - andExpect(jsonPath("$.data.total").isNumber())
    - name: statistics VO should contain correct structure with condition breakdown
      assertions:
        - assertNotNull(vo.getConditions())
        - assertNotNull(vo.getTotal())
        - assertTrue(vo.getConditions() instanceof Map)
    - name: getOfficeConditionStatistics should filter by projectId when provided
      assertions:
        - 'assertEquals(expectedProjectFilteredCount, result.getTotal())'
        - 'verify(mapper).selectOfficeConditionStats(eq(projectId), isNull())'
    - name: getOfficeConditionStatistics should filter by deptId when provided
      assertions:
        - 'assertEquals(expectedDeptFilteredCount, result.getTotal())'
        - 'verify(mapper).selectOfficeConditionStats(isNull(), eq(deptId))'
    - name: >-
        getOfficeConditionStatistics should group statistics by department for
        comparison
      assertions:
        - assertNotNull(result.getDepartmentStats())
        - 'assertEquals(expectedDeptCount, result.getDepartmentStats().size())'
        - assertTrue(result.getDepartmentStats().containsKey(deptName))
  e2eScenarios: []
  frameworkHint: junit5-spring
---
# Implement Office Asset Condition Statistics (P1)

## Context

Track office asset condition rates by department and project.

## Acceptance Criteria

1. Add mapper method: `selectOfficeConditionStats(Long projectId, Long deptId)`
2. Add service method: `getOfficeConditionStatistics(Long projectId, Long deptId)`
3. Add controller endpoint: `GET /asset/office/statistics/condition`
4. Return statistics VO:
5. Support filter by project and department
6. Group by department for comparison
## Technical Notes

- Reference: PRD Section 6.6.2 Point 4
- Pattern: Aggregation query
- Calculation: conditionRate = normalCount / totalCount * 100
