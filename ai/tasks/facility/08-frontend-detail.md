---
id: facility.frontend-detail
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn: [facility.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Facility Asset Detail Page

## Context

Detail page showing comprehensive facility asset information.

## Acceptance Criteria

1. Create `/src/views/asset/facility/detail.vue`

2. Implement tabbed layout:
   - Tab 1: Basic Information
   - Tab 2: Maintenance Records
   - Tab 3: Attachments

3. Display warranty status prominently:
   - Green if valid
   - Yellow if expiring soon (within 30 days)
   - Red if expired

4. Quick actions: Edit, Initiate Maintenance

5. Show audit info

## Technical Notes

- Reference: PRD section 6.3.2 point 3
