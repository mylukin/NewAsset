---
id: asset-office.frontend-form
module: asset-office
priority: 607
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-office.frontend-list
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
# Implement Office Asset Form Component (Frontend)

## Context

Create/Edit form component for office assets.

## Acceptance Criteria

1. Create form component `src/views/asset/office/form.vue`:
2. Form sections:
3. Form validation:
4. Footer: Save, Save & Add Another, Cancel
## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
