---
id: asset-parking.frontend-form
module: asset-parking
priority: 308
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-parking.frontend-list
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@parking'
---
# Implement Parking Asset Form Component (Frontend)

## Context

Create/Edit form component for parking assets.

## Acceptance Criteria

1. Create form component `src/views/asset/parking/form.vue`:
2. Form sections:
3. Form validation:
4. Footer: Save, Save & Add Another, Cancel
## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
