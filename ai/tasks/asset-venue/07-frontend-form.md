---
id: asset-venue.frontend-form
module: asset-venue
priority: 507
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-venue.frontend-list
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@venue'
---
# Implement Venue Asset Form Component (Frontend)

## Context

Create/Edit form component for venue assets.

## Acceptance Criteria

1. Create form component `src/views/asset/venue/form.vue`:
2. Form sections:
3. Form validation:
4. Footer: Save, Save & Add Another, Cancel
## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
