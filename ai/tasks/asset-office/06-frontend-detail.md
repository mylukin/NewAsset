---
id: asset-office.frontend-detail
module: asset-office
priority: 606
status: failing
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
# Implement Office Asset Detail Drawer (Frontend)

## Context

Detail drawer component for office assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/office/detail.vue`:
2. Implement 3 tabs:
3. Header actions: Edit, Initiate Maintenance, Delete
4. Status badge with color mapping (same as 9-state machine)
## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
