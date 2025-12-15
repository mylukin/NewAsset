---
id: asset-facility.frontend-list
module: asset-facility
priority: 406
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@facility'
---
# Implement Facility Asset List Page (Frontend)

## Context

Vue page for facility asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/facility.js`:
2. Create list page `src/views/asset/facility/index.vue`:
3. Status filter tabs: All, In Use, Under Maintenance, Fault
4. Permission controls with v-hasPermi
5. Empty state message
## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
