---
id: maintenance.frontend-detail
module: maintenance
priority: 709
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.frontend-list
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/maintenance/**/*.spec.ts
    tags:
      - '@maintenance'
---
# Implement Work Order Detail Page (Frontend)

## Context

Work order detail page with timeline, info panel, and action buttons.

## Acceptance Criteria

1. Create detail page `src/views/asset/maintenance/detail.vue`:
2. Header section:
3. Info panel (two columns):
4. Status timeline (el-timeline):
5. Result section (shown when WAIT_CONFIRM or CLOSED):
6. Action panel (bottom):
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 6 (Work Order Detail)
