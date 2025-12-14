---
id: venue.usage-statistics
module: venue
priority: 47
status: passing
version: 24
origin: manual
dependsOn:
  - venue.service
supersedes: []
tags:
  - statistics
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:21:01.762Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: a8101d7b9e884c0e61a0e39936939f7dee8b41a5
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:17:11.152Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/domain/entity/AssetVenueUsageTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetVenueUsageMapperTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetVenueUsageServiceTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetVenueUsageControllerTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateVenueUsageTableWithRequiredColumns
      assertions:
        - assertNotNull(venueUsage.getId())
        - assertNotNull(venueUsage.getVenueId())
        - assertNotNull(venueUsage.getUsageDate())
        - assertNotNull(venueUsage.getTimeSlot())
        - assertNotNull(venueUsage.getAttendance())
    - name: shouldCreateVenueUsageEntityWithAllFields
      assertions:
        - 'assertEquals(expectedVenueId, venueUsage.getVenueId())'
        - 'assertEquals(expectedUsageDate, venueUsage.getUsageDate())'
        - 'assertEquals(expectedTimeSlot, venueUsage.getTimeSlot())'
        - 'assertEquals(expectedAttendance, venueUsage.getAttendance())'
    - name: shouldInsertVenueUsageRecordViaMapper
      assertions:
        - 'assertEquals(1, insertedRows)'
        - assertNotNull(venueUsageMapper.selectById(venueUsage.getId()))
    - name: shouldListVenueUsageRecordsViaService
      assertions:
        - assertNotNull(usageList)
        - assertFalse(usageList.isEmpty())
        - assertTrue(usageList.size() > 0)
    - name: shouldGetUsageStatisticsEndpoint
      assertions:
        - 'assertEquals(200, response.getStatusCodeValue())'
        - assertNotNull(response.getBody())
    - name: shouldCalculateUsageFrequency
      assertions:
        - assertNotNull(statistics.getUsageFrequency())
        - assertTrue(statistics.getUsageFrequency() >= 0)
    - name: shouldCalculatePopularTimeSlots
      assertions:
        - assertNotNull(statistics.getPopularTimeSlots())
        - assertFalse(statistics.getPopularTimeSlots().isEmpty())
    - name: shouldCalculateAverageAttendance
      assertions:
        - assertNotNull(statistics.getAverageAttendance())
        - assertTrue(statistics.getAverageAttendance() >= 0)
  e2eScenarios: []
  frameworkHint: junit5-spring-boot
---
# Implement Venue Usage Statistics (P1)

## Context

Track venue usage through manual records for statistics before reservation system integration.

## Acceptance Criteria

1. Create `t_asset_venue_usage` table:
2. Create entity, mapper, service for venue usage records
3. Add endpoints:
4. Statistics include: usage frequency, popular time slots, average attendance
## Technical Notes

- Reference: PRD Section 6.5.2 Point 4
- Pattern: CRUD with aggregation
- Future: Integrate with reservation system
