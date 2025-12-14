---
id: office.frontend-list
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
# Create Office Asset List Page

## Acceptance Criteria

1. Create `/src/views/asset/office/index.vue`
2. Implement search filters:
3. Implement action toolbar with permissions
4. Implement data table with columns:
5. Conditional columns based on office type
