---
id: maintenance.frontend-form
module: maintenance
priority: 65
status: failing
version: 2
origin: manual
dependsOn:
  - maintenance.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Form Dialog

## Context

Vue dialog for creating maintenance orders with asset selection.

## Acceptance Criteria

1. Create order creation dialog
2. Form fields:
   - Asset selection (searchable dropdown with asset code/name)
   - Auto-populated: project, asset location
   - Order type (fault/repair/inspection)
   - Fault type (dictionary)
   - Title
   - Description (textarea)
   - Priority (一般/重要/紧急)
   - Require finish time (datetime picker)
   - Change asset status checkbox (optional)
   - Attachments (photo upload)
3. Support creation from asset detail page (pre-filled asset)
4. Validation for required fields
5. Preview order number generation

## Technical Notes

- Reference: PRD Section 6.1.2 (新建工单)
- Pattern: el-dialog with el-form
- Asset selector: Custom component with search
