---
id: maintenance.frontend-detail
module: maintenance
priority: 709
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/maintenance/**/*.spec.ts"
    tags:
      - "@maintenance"
---
# Implement Work Order Detail Page (Frontend)

## Context

Work order detail page with timeline, info panel, and action buttons.

## Acceptance Criteria

1. Create detail page `src/views/asset/maintenance/detail.vue`:
   - Route: /asset/maintenance/:id
   - Full page layout (not drawer)

2. Header section:
   - WO Number (large)
   - Priority badge
   - Status badge
   - Action buttons (dynamic based on status and role)

3. Info panel (two columns):
   - Left: Asset info (code, name, location) with link to asset detail
   - Right: WO info (requester, handler, dates, status linkage)

4. Status timeline (el-timeline):
   - Visual display of all status transitions
   - Each step shows: operator, time, description
   - Current status highlighted
   - Expandable operation details

5. Result section (shown when WAIT_CONFIRM or CLOSED):
   - Handler's result description
   - Material/labor costs
   - Completion attachments

6. Action panel (bottom):
   - Dynamic buttons based on current status and user role
   - Confirmation dialogs for each action
   - Assignment modal with user selector
   - Completion form with result fields
   - Verification form with pass/reject options

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 6 (Work Order Detail)
