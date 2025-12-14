---
id: parking.statistics
module: parking
priority: 33
status: failing
version: 9
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
---
# Implement Parking Asset Statistics

## Context

Provide utilization statistics for parking assets.

## Acceptance Criteria

1. Add mapper method: `selectParkingStatistics(Long projectId, String parkingZone)`
2. Add service method: `getParkingStatistics(Long projectId, String parkingZone)`
3. Add controller endpoint: `GET /asset/parking/statistics`
4. Return statistics VO:
   - totalCount (总车位数)
   - selfUseCount/selfUseRate (自用车位)
   - rentCount/rentRate (出租车位)
   - idleCount/idleRate (空置车位)
5. Support filter by project and parking zone
6. Use SQL aggregation for efficiency

## Technical Notes

- Reference: PRD Section 6.4.2 Point 4
- Pattern: Aggregation query
- SQL: COUNT with CASE WHEN
