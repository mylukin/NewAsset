---
id: dashboard.ops-home
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [dashboard.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Operations Staff Dashboard Page

## Context

Dashboard for maintenance/operations staff showing their pending work and statistics.

## Acceptance Criteria

1. Create `/src/views/dashboard/OpsHome.vue`

2. Implement "My Pending Orders" section:
   - List of orders waiting for action
   - Sorted by priority (urgent first), then create time
   - Columns: Order No, Asset, Priority (tag), Status, Require Finish Time
   - Direct action buttons: Accept (if WAIT_ACCEPT), Start (if accepted)
   - Click to view order detail

3. Implement my statistics cards:
   - Orders Completed This Month
   - Orders In Progress
   - Pending Accept Count

4. Implement optional:
   - Calendar view of due orders
   - Quick accept button

5. Highlight overdue items in red

## Technical Notes

- Reference: PRD section 7.1 point 2
