---
id: maintenance.frontend-form
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
# Create Maintenance Order Form Component

## Context

Form for creating maintenance orders with asset selection and attachment support.

## Acceptance Criteria

1. Create `/src/views/asset/maint/components/OrderForm.vue`

2. Implement form fields:
   - Asset (required, asset selector component)
     - Auto-fill from asset detail if opened from asset page
     - Search by code or name
     - Show asset info preview on selection
   - Project (auto-filled from asset, read-only)
   - Order Type (required, dropdown: 故障/报修/巡检/维保)
   - Fault Type (dropdown based on order type)
   - Title (required)
   - Description (required, textarea)
   - Priority (required, dropdown)
   - Require Finish Time (date picker)
   - Change Asset Status checkbox (optional)
   - Attachments (multi-file upload)

3. Implement asset selector component:
   - Search input
   - Recent assets dropdown
   - Asset info preview (code, name, location, status)

4. Implement validation rules

5. Support mode when opened from asset detail (asset pre-selected)

## Technical Notes

- Reference: PRD section 6.1.2 (New Order)
