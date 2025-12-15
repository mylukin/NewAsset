---
id: dashboard.frontend-layout
module: dashboard
priority: 903
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - dashboard.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/dashboard/**/*.spec.ts
    tags:
      - '@dashboard'
---
# Implement Dashboard Layout (Frontend)

## Context

Vue dashboard page with role-based layout.

## Acceptance Criteria

1. Create API file `src/api/asset/dashboard.js`:
2. Create dashboard page `src/views/asset/dashboard/index.vue`:
3. Role-based layout variants:
4. Role detection from Vuex store user info
5. Loading states for each section
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 1 (Role-Based Dashboard)
- Use v-if for role-based rendering
