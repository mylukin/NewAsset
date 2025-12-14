---
id: parking.statistics
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [parking.service]
supersedes: []
tags: [backend, statistics, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/parking/**/*.test.*"
---
# Implement Parking Asset Statistics

## Context

Provide utilization statistics for parking assets by project and zone.

## Acceptance Criteria

1. Create `ParkingAssetStatistics` VO:
   - `totalCount` - 总车位数
   - `selfUseCount` / `selfUseRatio` - 自用车位
   - `rentCount` / `rentRatio` - 出租车位
   - `idleCount` / `idleRatio` - 空置车位
   - `projectId`
   - `parkingZone` - for zone-level stats

2. Implement statistics query in mapper:
   ```sql
   SELECT
     parking_zone,
     COUNT(*) as total_count,
     SUM(CASE WHEN status = 'AVAILABLE_SELF' THEN 1 ELSE 0 END) as self_use_count,
     ...
   FROM t_asset a
   JOIN t_asset_parking p ON a.id = p.asset_id
   WHERE a.asset_type = 'PARKING' AND a.del_flag = '0'
   GROUP BY parking_zone
   ```

3. Support filtering by project and zone

4. Cache statistics for 5 minutes

## Technical Notes

- Reference: PRD section 6.4.2 point 4
