---
id: venue.frontend-detail
module: venue
priority: 15
status: failing
version: 1
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - P0
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create Venue Asset Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/venue/detail.vue`
2. Implement tabbed layout:
3. Display current status prominently with color
4. Show open time description clearly
5. Quick actions: Edit, Change Status, Initiate Maintenance
