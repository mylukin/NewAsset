---
id: asset-facility.frontend-detail
module: asset-facility
priority: 407
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
# Implement Facility Asset Detail Drawer (Frontend)

## Context

Detail drawer component for facility assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/facility/detail.vue`:
2. Implement 3 tabs:
3. Header actions: Edit, Initiate Maintenance, Delete
4. Warranty status badge:
## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
