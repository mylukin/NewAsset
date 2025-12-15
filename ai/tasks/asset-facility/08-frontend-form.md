---
id: asset-facility.frontend-form
module: asset-facility
priority: 408
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.frontend-list
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
# Implement Facility Asset Form Component (Frontend)

## Context

Create/Edit form component for facility assets.

## Acceptance Criteria

1. Create form component `src/views/asset/facility/form.vue`:
2. Form sections:
3. Form validation:
4. Footer: Save, Save & Add Another, Cancel
## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
