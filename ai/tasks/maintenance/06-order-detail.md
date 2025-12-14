---
id: maintenance.order-detail
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-log, maintenance.order-workflow]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Maintenance Order Detail View

## Context

Order detail shows comprehensive information including asset details, order info, processing timeline, and allows workflow actions.

## Acceptance Criteria

1. Backend API `GET /asset/maint/order/{id}`:
   - Returns MaintOrderDetailVO:
     - Order info (all fields)
     - Asset info (code, name, location, status)
     - Handler info (name, phone)
     - Requester info
     - Log timeline entries
     - Attachments
2. Frontend page `views/asset/maint/orderDetail.vue`:
   - Header: order number, status badge, priority
   - Section: Asset Information
     - Asset code, name, type
     - Location, current status
     - Link to asset detail
   - Section: Order Details
     - Type, fault type, description
     - Required finish time
     - Actual handler, completion time
   - Section: Timeline
     - Chronological log entries
     - Each entry shows: operator, action, time, notes
   - Section: Attachments
     - Before/after photos
     - Documents
3. Action buttons (status-dependent):
   - Assign (WAIT_ASSIGN)
   - Accept (WAIT_ACCEPT)
   - Update Progress (PROCESSING)
   - Complete (PROCESSING)
   - Confirm/Reject (WAIT_CONFIRM)
   - Cancel (non-closed)
4. Action dialogs:
   - Assign: select handler
   - Complete: add result notes, photos
   - Confirm: pass/fail with notes

## Technical Notes

- Timeline uses el-timeline component
- Photos in lightbox viewer
- Consider print view for documentation
