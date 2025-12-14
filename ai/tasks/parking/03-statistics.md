---
id: parking.statistics
module: parking
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [parking.crud]
supersedes: []
tags: [feature, statistics, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Parking Asset Utilization Statistics

## Context

Parking statistics focus on utilization rates: total spaces, self-use, rented, and vacant counts with percentages. Supports filtering by project and zone.

## Acceptance Criteria

1. Backend API `GET /asset/parking/statistics`:
   - Query params: projectId, parkingZone
   - Returns ParkingStatisticsVO:
     - totalCount
     - selfUseCount, selfUseRatio
     - rentedCount, rentedRatio
     - vacantCount, vacantRatio
   - Data permission applied
2. Backend service:
   - Group by current usage status
   - Filter by zone if specified
   - Calculate utilization rate
3. Frontend statistics component:
   - Metric cards at top of page
   - Zone dropdown for filtering
   - Visual utilization indicator
   - Click metric to filter table
4. Integration:
   - Refresh after CRUD operations
   - Include in dashboard summary
5. Zone-level breakdown (optional):
   - Show stats per parking zone
   - Comparison view

## Technical Notes

- Similar to house statistics
- Zone dimension adds complexity
- Consider pie/donut chart for visualization
