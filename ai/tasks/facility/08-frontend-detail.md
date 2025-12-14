---
id: facility.frontend-detail
module: facility
priority: 27
status: failing
version: 14
origin: manual
dependsOn:
  - facility.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Detail Drawer

## Context

Detail view for facility assets with maintenance records and attachments.

## Acceptance Criteria

1. Create detail drawer component
2. Implement tabbed layout:
   - Tab 1: Basic Info - all fields
   - Tab 2: Maintenance Records
   - Tab 3: Attachments
3. Action buttons: Edit, Initiate Maintenance, Upload Attachment
4. Display maintenance org contact prominently for quick access

## Technical Notes

- Reference: PRD Section 6.3.2 Point 3
- Pattern: el-drawer with el-tabs
