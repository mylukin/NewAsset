---
id: maintenance.frontend-list
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [maintenance.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Maintenance Order List Page

## Context

Maintenance order list with filtering by status, priority, and role-based views.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderList.vue`

2. Implement search filters:
   - Project selector
   - Asset type selector
   - Order status (multi-select)
   - Priority selector
   - Handler selector (for admins)
   - Create date range
   - Search/Reset buttons

3. Implement quick filter tabs:
   - All Orders
   - Pending Assign
   - Pending Accept
   - Processing
   - Pending Confirm
   - Closed

4. Implement action toolbar:
   - Create Order button
   - Export button

5. Implement data table with columns:
   - Order No
   - Asset Code
   - Asset Name
   - Project
   - Order Type
   - Priority (with color)
   - Status (with color tag)
   - Handler
   - Requester
   - Create Time
   - Require Finish Time
   - Actions (context-sensitive based on status)

6. Row actions based on status and role:
   - View Detail (always)
   - Assign (when WAIT_ASSIGN and has permission)
   - Accept (when WAIT_ACCEPT and is assigned handler)
   - Complete (when PROCESSING and is handler)
   - Confirm/Reject (when WAIT_CONFIRM and has permission)
   - Cancel (when not closed)

## Technical Notes

- Reference: PRD section 6.1.2
- TECH.md section 7.2
