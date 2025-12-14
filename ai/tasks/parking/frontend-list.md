---
id: parking.frontend-list
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
# Create Parking Asset List Page

## Acceptance Criteria

1. Create `/src/views/asset/parking/index.vue`
2. Implement search filters:
3. Implement action toolbar with permissions
4. Implement data table with columns:
5. Optionally show statistics cards above table
