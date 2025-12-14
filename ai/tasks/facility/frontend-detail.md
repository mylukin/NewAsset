---
id: facility.frontend-detail
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn:
  - facility.frontend-api
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
# Create Facility Asset Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/facility/detail.vue`
2. Implement tabbed layout:
3. Display warranty status prominently:
4. Quick actions: Edit, Initiate Maintenance
5. Show audit info
