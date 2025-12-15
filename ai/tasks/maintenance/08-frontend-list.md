---
id: maintenance.frontend-list
module: maintenance
priority: 708
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/maintenance/**/*.spec.ts
    tags:
      - '@maintenance'
---
# Implement Work Order List Page (Frontend)

## Context

Vue page for work order list with view tabs and filters.

## Acceptance Criteria

1. Create API file `src/api/asset/maintenance.js`:
2. Create list page `src/views/asset/maintenance/index.vue`:
3. Status badges with colors:
4. Priority badges:
5. Permission controls with v-hasPermi
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 5 (Work Order List)
