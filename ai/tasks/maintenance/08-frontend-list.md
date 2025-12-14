---
id: maintenance.frontend-list
module: maintenance
priority: 64
status: failing
version: 19
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
# Create Maintenance Order List Page

## Context

Vue component for maintenance order list with workflow actions.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderList.vue`
2. Implement search filters:
   - Project, asset type, order status, priority
   - Handler, requester, date range
3. Data table columns:
   - Order no, asset code, asset name, project
   - Fault type, priority, status
   - Handler, requester, create time, require finish time
   - Actions
4. Action buttons based on status and role:
   - View detail
   - Assign (for admin when WAIT_ASSIGN)
   - Accept (for handler when WAIT_ACCEPT)
   - Complete (for handler when PROCESSING)
   - Confirm/Reject (for verifier when WAIT_CONFIRM)
   - Cancel (with permission)
5. Status displayed with colored badges
6. Sort by create time and require finish time

## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: RuoYi list page with workflow actions
