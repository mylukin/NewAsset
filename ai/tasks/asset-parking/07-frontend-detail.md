---
id: asset-parking.frontend-detail
module: asset-parking
priority: 307
status: passing
version: 3
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
# Implement Parking Asset Detail Drawer (Frontend)

## Context

Detail drawer component for parking assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/parking/detail.vue`:
2. Implement 3 tabs:
3. Header actions: Edit, Initiate Maintenance, Delete
4. Status badge with color mapping
## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
