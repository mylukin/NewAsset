---
id: house.frontend-list
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
# Create House Asset List Page

## Acceptance Criteria

1. Create `/src/views/asset/house/index.vue`:
2. Implement search/filter section:
3. Implement action toolbar:
4. Implement data table with columns:
5. Implement pagination component
6. Use RuoYi's `v-hasPermi` directive for button permissions
7. Support column show/hide configuration (optional P1)
8. Implement row click to show detail drawer/dialog
