---
id: house.frontend-detail
module: house
priority: 18
status: failing
version: 18
origin: manual
dependsOn:
  - house.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Detail Page/Drawer

## Context

Detail view showing all house asset information, maintenance records, and attachments with action buttons.

## Acceptance Criteria

1. Create detail drawer or modal component
2. Implement tabbed layout:
   - Tab 1: Basic Info - all asset and extension fields
   - Tab 2: Maintenance Records - list from maintenance module
   - Tab 3: Attachments - uploaded files with preview/download
3. Display fields with proper formatting:
   - Dates formatted, numbers with units
   - Status with colored badges
   - Dictionary values with labels
4. Action buttons:
   - Edit (with permission)
   - Initiate Maintenance
   - Upload Attachment
   - Copy Asset
5. Show asset code prominently
6. Link to related maintenance orders

## Technical Notes

- Reference: PRD Section 6.2.2 Point 3
- Pattern: el-drawer with el-tabs
- Integration: Call maintenance API for records
