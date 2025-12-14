---
id: house.statistics
module: house
priority: 14
status: failing
version: 13
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
---
# Implement House Asset Statistics

## Context

Provide statistics view for house assets including vacancy rates and usage distribution.

## Acceptance Criteria

1. Add method to `AssetHouseMapper`: `selectHouseStatistics(Long projectId)`
2. Add service method: `getHouseStatistics(Long projectId)`
3. Add controller endpoint: `GET /asset/house/statistics`
4. Return statistics VO containing:
   - totalCount (总房源数量)
   - selfUseCount (自用数量)
   - selfUseRate (自用占比)
   - rentCount (出租数量)
   - rentRate (出租占比)
   - idleCount (空置数量)
   - idleRate (空置率)
5. Support project filter parameter
6. Use efficient SQL aggregation (COUNT with CASE)

## Technical Notes

- Reference: PRD Section 6.2.2 Point 4
- Pattern: Aggregation query with VO response
- SQL: GROUP BY with conditional counting
