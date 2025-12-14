---
id: venue.entity
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/venue/**/*.test.*"
---
# Create Venue Asset Entity

## Context

Venue assets represent activity rooms, sports facilities, and meeting rooms with capacity and availability info.

## Acceptance Criteria

1. Create `VenueAsset` entity in `com.ruoyi.asset.domain.entity`:
   - Map to `t_asset_venue` table (asset_id as PK/FK)

2. Include venue-specific fields:
   - `venueType` (场馆类型) - String (dict: venue_type)
   - `capacity` (容纳人数) - Integer
   - `useMode` (使用方式) - String (免费/收费/内部使用)
   - `openTimeDesc` (开放时间段描述) - String
   - `currentVenueStatus` (当前状态) - String (可预约/占用/维护中)

3. Create `VenueStatusEnum`:
   - `AVAILABLE` - 可预约
   - `OCCUPIED` - 占用中
   - `MAINTENANCE` - 维护中

4. Create `VenueAssetVO`, `VenueAssetCreateDTO`, `VenueAssetUpdateDTO`

## Technical Notes

- Reference: TECH.md section 4.1.2 (t_asset_venue)
