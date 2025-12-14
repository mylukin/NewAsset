---
id: venue.entity
module: venue
priority: 39
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Entity

## Context

Venue assets represent spaces like sports facilities, conference rooms, and activity centers with capacity and availability info.

## Acceptance Criteria

1. Create `AssetVenue` entity referencing `Asset` base
2. Map extension fields from `t_asset_venue`:
   - venueType (场馆类型: 篮球场/羽毛球馆/会议室等)
   - capacity (容纳人数)
   - useMode (使用方式: 免费/收费/内部使用)
   - openTimeDesc (开放时间段描述)
   - currentVenueStatus (当前状态: 可预约/占用/维护中)
3. Create VO and DTO classes
4. Add validation annotations

## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_venue)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetVenue`
