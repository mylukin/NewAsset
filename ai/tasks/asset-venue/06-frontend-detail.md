---
id: asset-venue.frontend-detail
module: asset-venue
priority: 506
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
# Implement Venue Asset Detail Drawer (Frontend)

## Context

Detail drawer component for venue assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/venue/detail.vue`:
2. Implement 3 tabs:
3. Header actions: Edit, Initiate Maintenance, Delete
4. Availability status badge:
## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
