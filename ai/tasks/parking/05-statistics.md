---
id: parking.statistics
module: parking
priority: 33
status: passing
version: 24
origin: manual
dependsOn:
  - parking.service
supersedes: []
tags:
  - statistics
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:59:33.129Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 2c035433380d7e4f80ebcf47794308a7d2fb6116
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:57:35.794Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetParkingMapperStatisticsTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetParkingServiceStatisticsTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetParkingControllerStatisticsTest.java
    e2e: []
  unitTestCases:
    - name: selectParkingStatistics should return statistics with correct totalCount
      assertions:
        - assertNotNull(statistics)
        - 'assertEquals(expectedTotal, statistics.getTotalCount())'
    - name: selectParkingStatistics should filter by projectId when provided
      assertions:
        - 'assertEquals(projectFilteredCount, statistics.getTotalCount())'
    - name: selectParkingStatistics should filter by parkingZone when provided
      assertions:
        - 'assertEquals(zoneFilteredCount, statistics.getTotalCount())'
    - name: >-
        getParkingStatistics should delegate to mapper and return
        ParkingStatisticsVO
      assertions:
        - 'verify(parkingMapper).selectParkingStatistics(projectId, parkingZone)'
        - assertNotNull(result)
    - name: getParkingStatistics should calculate selfUseRate correctly
      assertions:
        - 'assertEquals(BigDecimal.valueOf(33.33), result.getSelfUseRate())'
    - name: getParkingStatistics should calculate rentRate correctly
      assertions:
        - 'assertEquals(BigDecimal.valueOf(50.00), result.getRentRate())'
    - name: getParkingStatistics should calculate idleRate correctly
      assertions:
        - 'assertEquals(BigDecimal.valueOf(16.67), result.getIdleRate())'
    - name: GET /asset/parking/statistics should return 200 with statistics data
      assertions:
        - status().isOk()
        - jsonPath('$.data.totalCount').exists()
        - jsonPath('$.data.selfUseCount').exists()
        - jsonPath('$.data.rentCount').exists()
        - jsonPath('$.data.idleCount').exists()
    - name: GET /asset/parking/statistics should support projectId filter parameter
      assertions:
        - status().isOk()
        - jsonPath('$.data.totalCount').value(expectedProjectCount)
    - name: >-
        GET /asset/parking/statistics should support parkingZone filter
        parameter
      assertions:
        - status().isOk()
        - jsonPath('$.data.totalCount').value(expectedZoneCount)
    - name: statistics query should use SQL aggregation (COUNT with CASE WHEN)
      assertions:
        - assertTrue(sqlContainsCountWithCase)
  e2eScenarios: []
  frameworkHint: junit5-spring-boot-test
---
# Implement Parking Asset Statistics

## Context

Provide utilization statistics for parking assets.

## Acceptance Criteria

1. Add mapper method: `selectParkingStatistics(Long projectId, String parkingZone)`
2. Add service method: `getParkingStatistics(Long projectId, String parkingZone)`
3. Add controller endpoint: `GET /asset/parking/statistics`
4. Return statistics VO:
5. Support filter by project and parking zone
6. Use SQL aggregation for efficiency
## Technical Notes

- Reference: PRD Section 6.4.2 Point 4
- Pattern: Aggregation query
- SQL: COUNT with CASE WHEN
