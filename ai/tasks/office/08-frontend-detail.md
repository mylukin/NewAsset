---
id: office.frontend-detail
module: office
priority: 11
status: failing
version: 1
origin: manual
dependsOn: [office.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Office Asset Detail Page

## Context

Detail page for office assets with IT-specific information display.

## Acceptance Criteria

1. Create `/src/views/asset/office/detail.vue`

2. Implement tabbed layout:
   - Tab 1: Basic Info + Type-specific Info
   - Tab 2: Maintenance Records
   - Tab 3: Attachments

3. For IT devices, display:
   - Serial No prominently
   - OS and Config info
   - QR code for asset tag (optional)

4. Quick actions: Edit, Initiate Maintenance

## Technical Notes

- Reference: PRD section 6.6.2 point 3
