---
id: office.frontend-detail
module: office
priority: 11
status: failing
version: 1
origin: manual
dependsOn:
  - office.frontend-api
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
# Create Office Asset Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/office/detail.vue`
2. Implement tabbed layout:
3. For IT devices, display:
4. Quick actions: Edit, Initiate Maintenance
