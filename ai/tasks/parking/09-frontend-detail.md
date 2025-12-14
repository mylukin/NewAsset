---
id: parking.frontend-detail
module: parking
priority: 37
status: failing
version: 10
origin: manual
dependsOn:
  - parking.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Detail Drawer

## Context

Detail view for parking assets with maintenance records.

## Acceptance Criteria

1. Create detail drawer component
2. Tabbed layout:
   - Tab 1: Basic Info
   - Tab 2: Maintenance Records
   - Tab 3: Attachments
3. Action buttons: Edit, Initiate Maintenance
4. Display usage info prominently (plate no, current user)

## Technical Notes

- Reference: PRD Section 6.4.2 Point 3
- Pattern: el-drawer with el-tabs
