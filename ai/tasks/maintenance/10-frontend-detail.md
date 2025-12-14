---
id: maintenance.frontend-detail
module: maintenance
priority: 66
status: failing
version: 13
origin: manual
dependsOn:
  - maintenance.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Detail Page

## Context

Detail page showing order info, timeline, and workflow actions.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderDetail.vue`
2. Layout sections:
   - Header: Order no, status badge, priority tag
   - Basic info: Asset, project, fault type, description
   - Timeline: Status transitions with operator and notes
   - Attachments: Before/after photos
   - Actions: Context-appropriate workflow buttons
3. Timeline display:
   - Chronological order of all status changes
   - Show operator name, time, and content
   - Visual timeline component
4. Workflow action dialogs:
   - Assign: Select handler
   - Complete: Result description, attachments
   - Confirm: Approve/Reject with comment
5. Link to related asset detail

## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: Detail page with timeline
- Component: el-timeline for status history
