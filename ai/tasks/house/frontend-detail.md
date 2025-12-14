---
id: house.frontend-detail
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn:
  - house.frontend-api
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
# Create House Asset Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/house/detail.vue` (or drawer component):
2. Implement tabbed layout:
3. Show breadcrumb navigation
4. Handle loading and error states
5. Quick actions in header:
6. Display audit info (created by, updated by, timestamps)
