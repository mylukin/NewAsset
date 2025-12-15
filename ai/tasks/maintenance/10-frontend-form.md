---
id: maintenance.frontend-form
module: maintenance
priority: 710
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.frontend-list
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
# Implement Work Order Create Form (Frontend)

## Context

Work order creation form, can be opened from asset detail or maintenance list.

## Acceptance Criteria

1. Create form component `src/views/asset/maintenance/form.vue`:
2. Form fields:
3. Pre-fill behavior:
4. Form validation:
5. Submit creates work order with status WAIT_ASSIGN
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 4 (Asset Form - initiate maintenance button)
