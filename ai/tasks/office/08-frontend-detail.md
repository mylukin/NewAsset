---
id: office.frontend-detail
module: office
priority: 55
status: failing
version: 9
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Detail Drawer

## Context

Detail view for office assets with user info and maintenance records.

## Acceptance Criteria

1. Create detail drawer component
2. Tabbed layout:
   - Tab 1: Basic Info - all fields with IT-specific section
   - Tab 2: Maintenance Records
   - Tab 3: Attachments
3. Action buttons: Edit, Initiate Maintenance
4. Display user assignment prominently

## Technical Notes

- Reference: PRD Section 6.6.2 Point 3
- Pattern: el-drawer with el-tabs
