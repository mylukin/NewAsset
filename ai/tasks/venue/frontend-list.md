---
id: venue.frontend-list
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
# Create Venue Asset List Page

## Acceptance Criteria

1. Create `/src/views/asset/venue/index.vue`
2. Implement search filters:
3. Implement action toolbar with permissions
4. Implement data table with columns:
5. Quick status toggle button in row
