---
id: asset-venue.database-schema
module: asset-venue
priority: 501
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - database
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-venue/**/*.test.*"
---
# Create Venue Asset Extension Table

## Context

Extension table for venue-specific fields (community spaces). 1:1 relationship with t_asset base table.

## Acceptance Criteria

1. Create `t_asset_venue` table with venue-specific fields:
   - asset_id (bigint, PK, FK to t_asset.id)
   - venue_type (varchar(20)) - 场馆类型 (basketball, badminton, meeting_room, activity_room, gym, etc.)
   - capacity (int) - 容纳人数
   - usage_mode (varchar(20)) - 使用方式 (free, paid, internal)
   - open_time_desc (varchar(500)) - 对外开放时间段描述
   - availability_status (varchar(20)) - 当前状态 (available, occupied, maintaining)

2. Add foreign key constraint to t_asset.id

3. SQL migration file created at `sql/asset_venue.sql`

4. Add dictionary entries:
   - venue_type: basketball(篮球场), badminton(羽毛球馆), meeting_room(会议室), activity_room(活动室), gym(健身房), swimming_pool(游泳池), other(其他)
   - usage_mode: free(免费), paid(收费), internal(内部使用)
   - availability_status: available(可预约), occupied(使用中), maintaining(维护中)

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Venue Asset Module)
- PRD: docs/PRD.md Section 6.5 (场馆资产模块)
