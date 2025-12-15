---
id: asset-house.statistics
module: asset-house
priority: 205
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/asset-house/**/*.test.*"
    cases:
      - "should calculate vacancy rate correctly"
      - "should calculate rental rate correctly"
---
# Implement House Asset Statistics

## Context

Statistics calculation for house assets: vacancy rate, rental rate, usage distribution.

## Acceptance Criteria

1. Add statistics methods to `IAssetHouseService`:
   - `HouseStatsVO getStats(Long projectId)`
   - `List<UsageDistributionVO> getUsageDistribution(Long projectId)`

2. Statistics calculations:
   - Total count: COUNT where asset_type='HOUSE' and del_flag='0'
   - Self-use count: COUNT where current_usage='self_use'
   - Rental count: COUNT where current_usage='rented'
   - Idle count: COUNT where current_usage='idle'
   - Vacancy rate: idle_count / total_count * 100
   - Rental rate: rental_count / total_count * 100

3. Create `HouseStatsVO`:
   ```java
   class HouseStatsVO {
       Integer totalCount;
       Integer selfUseCount;
       Integer rentalCount;
       Integer idleCount;
       BigDecimal vacancyRate;
       BigDecimal rentalRate;
   }
   ```

4. Add API endpoint:
   **GET /asset/house/stats**
   - Query: projectId (required)
   - Returns: HouseStatsVO
   - Permission: asset:house:list

5. Unit tests for calculation accuracy

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Dashboard statistics)
- Used by dashboard module for KPI cards
