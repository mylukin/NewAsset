---
id: asset-parking.statistics
module: asset-parking
priority: 305
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/asset-parking/**/*.test.*"
    cases:
      - "should calculate utilization rate correctly"
---
# Implement Parking Asset Statistics

## Context

Statistics calculation for parking assets: utilization rate by zone.

## Acceptance Criteria

1. Add statistics methods to `IAssetParkingService`:
   - `ParkingStatsVO getStats(Long projectId)`
   - `List<ZoneStatsVO> getStatsByZone(Long projectId)`

2. Statistics calculations:
   - Total count: COUNT where asset_type='PARKING' and del_flag='0'
   - Self-use count: COUNT where status='available_self'
   - Rented count: COUNT where status='available_rent'
   - Idle count: COUNT where status='available_idle'
   - Utilization rate: (total - idle) / total * 100

3. Create `ParkingStatsVO`:
   - totalCount, selfUseCount, rentedCount, idleCount
   - utilizationRate (BigDecimal)

4. Create `ZoneStatsVO`:
   - zoneName, totalCount, usedCount, idleCount, utilizationRate

5. Add API endpoint:
   **GET /asset/parking/stats**
   - Query: projectId (required)
   - Returns: ParkingStatsVO
   - Permission: asset:parking:list

6. Unit tests for calculation accuracy

## Technical Notes

- Used by dashboard module for KPI cards
- Reference: ai/tasks/asset-house/05-statistics.md
