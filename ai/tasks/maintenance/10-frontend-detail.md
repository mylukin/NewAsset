---
id: maintenance.frontend-detail
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
# Create Maintenance Order Detail Page

## Context

Order detail page with timeline, processing forms, and context-sensitive actions.

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderDetail.vue`

2. Implement order info header:
   - Order No, Status tag, Priority tag
   - Asset info link (click to view asset)
   - Requester, Handler info
   - Create time, Require finish time

3. Implement timeline/log section:
   - Show all order logs chronologically
   - Each log: operator, operation type, time, content
   - Attached images inline or as thumbnails

4. Implement action panel (based on status):

   **WAIT_ASSIGN:**
   - Assign form: handler selector, submit button

   **WAIT_ACCEPT (if current user is handler):**
   - Accept button

   **PROCESSING (if current user is handler):**
   - Process form: description textarea, attachments, submit button

   **WAIT_CONFIRM (if has confirm permission):**
   - Confirm/Reject buttons
   - Reason input for reject

5. Implement asset info panel:
   - Show linked asset basic info
   - Link to asset detail

6. Cancel button (when applicable)

## Technical Notes

- Reference: PRD section 6.1.2
- TECH.md section 7.2
