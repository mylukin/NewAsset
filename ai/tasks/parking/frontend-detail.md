---
id: parking.frontend-detail
module: parking
priority: 9
status: failing
version: 1
origin: manual
dependsOn:
  - parking.frontend-api
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
# Create Parking Asset Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/parking/detail.vue`
2. Implement tabbed layout:
3. Display usage status prominently
4. Quick actions: Edit, Initiate Maintenance
