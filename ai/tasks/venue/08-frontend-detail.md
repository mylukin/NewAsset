---
id: venue.frontend-detail
module: venue
priority: 46
status: failing
version: 9
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Detail Drawer

## Context

Detail view for venue assets with maintenance records and open time info.

## Acceptance Criteria

1. Create detail drawer component
2. Tabbed layout:
   - Tab 1: Basic Info - all fields including open time description
   - Tab 2: Maintenance Records
   - Tab 3: Attachments
3. Action buttons: Edit, Initiate Maintenance
4. Display capacity and availability prominently

## Technical Notes

- Reference: PRD Section 6.5.2 Point 3
- Pattern: el-drawer with el-tabs
