---
id: house.statistics
module: house
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [house.list]
supersedes: []
tags: [feature, statistics, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset Statistics View

## Context

The statistics view provides key metrics for house assets: total count, self-use count/ratio, rented count/ratio, and vacant count/ratio. Supports project dimension filtering.

## Acceptance Criteria

1. Backend API `GET /asset/house/statistics`:
   - Query param: projectId (optional, for filtering)
   - Returns HouseStatisticsVO:
     - totalCount
     - selfUseCount, selfUseRatio
     - rentedCount, rentedRatio
     - vacantCount, vacantRatio
   - Data permission applied
2. Backend service:
   - Single query with GROUP BY current_usage
   - Calculate ratios in service layer
   - Handle division by zero (0 total)
3. Frontend statistics component:
   - Card layout with metrics
   - Each card shows count and percentage
   - Visual indicators (colors/icons)
   - Project selector to filter
4. Integration with house list page:
   - Statistics shown above the table
   - Click on metric filters the list
   - Real-time update after CRUD operations
5. Export capability:
   - Include statistics in Excel export header

## Technical Notes

- Use MyBatis selectOne with GROUP BY
- Cache statistics for performance (optional)
- Donut chart visualization (optional enhancement)
