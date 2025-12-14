---
id: house.statistics
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [house.service]
supersedes: []
tags: [backend, statistics, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/house/**/*.test.*"
---
# Implement House Asset Statistics

## Context

Provide statistical views for house assets including vacancy rates, usage distribution by project.

## Acceptance Criteria

1. Create `HouseAssetStatistics` VO:
   - `totalCount` - 总房源数量
   - `selfUseCount` - 自用数量
   - `selfUseRatio` - 自用占比
   - `rentCount` - 出租数量
   - `rentRatio` - 出租占比
   - `idleCount` - 空置数量
   - `idleRatio` - 空置占比 (vacancy rate)
   - `projectId` - 项目ID (for filtering)

2. Add to `IHouseAssetService`:
   - `HouseAssetStatistics getStatistics(Long projectId)`
   - `List<HouseAssetStatistics> getStatisticsByProject()` - all projects

3. Implement statistics query in mapper:
   ```sql
   SELECT
     COUNT(*) as total_count,
     SUM(CASE WHEN status = 'AVAILABLE_SELF' THEN 1 ELSE 0 END) as self_use_count,
     SUM(CASE WHEN status = 'AVAILABLE_RENT' THEN 1 ELSE 0 END) as rent_count,
     SUM(CASE WHEN status = 'AVAILABLE_IDLE' THEN 1 ELSE 0 END) as idle_count
   FROM t_asset
   WHERE asset_type = 'HOUSE' AND del_flag = '0'
   ```

4. Add controller endpoint:
   - `GET /asset/house/statistics` - get statistics
   - `GET /asset/house/statistics/by-project` - grouped by project

5. Calculate ratios in service layer (avoid division in SQL)

## Technical Notes

- Reference: PRD section 6.2.2 point 4
- Cache statistics for 5 minutes to reduce DB load
