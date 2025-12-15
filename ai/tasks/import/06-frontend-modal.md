---
id: import.frontend-modal
module: import
priority: 806
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - import.controller
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/import/**/*.spec.ts
    tags:
      - '@import'
---
# Implement Import Modal (Frontend)

## Context

3-step import wizard modal component.

## Acceptance Criteria

1. Create API file `src/api/asset/import.js`:
2. Create modal component `src/views/asset/components/ImportModal.vue`:
3. Implement 3-step wizard (el-steps):
4. Success state:
5. Error handling:
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 7 (Import Modal)
- Reusable across all asset types
