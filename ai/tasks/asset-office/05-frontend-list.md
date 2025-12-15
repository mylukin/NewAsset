---
id: asset-office.frontend-list
module: asset-office
priority: 605
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-office.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@office'
---
# Implement Office Asset List Page (Frontend)

## Context

Vue page for office asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/office.js`:
2. Create list page `src/views/asset/office/index.vue`:
3. Status filter tabs: All, In Use (Available-Self), Under Maintenance, Fault
4. Permission controls with v-hasPermi
5. Empty state message
## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
