---
id: venue.frontend-list
module: venue
priority: 15
status: failing
version: 1
origin: manual
dependsOn: [venue.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Venue Asset List Page

## Context

Venue asset list page with filtering and availability display.

## Acceptance Criteria

1. Create `/src/views/asset/venue/index.vue`

2. Implement search filters:
   - Project selector
   - Venue Type (篮球场/羽毛球馆/会议室/活动室)
   - Current Venue Status (可预约/占用/维护中)
   - Search/Reset buttons

3. Implement action toolbar with permissions

4. Implement data table with columns:
   - Asset Code
   - Venue Name
   - Project
   - Location
   - Venue Type
   - Capacity
   - Use Mode (免费/收费)
   - Current Status (with color tag)
   - Open Time
   - Actions

5. Quick status toggle button in row

## Technical Notes

- Reference: PRD section 6.5.2
