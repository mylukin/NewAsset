---
id: maintenance.frontend-form
module: maintenance
priority: 710
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/maintenance/**/*.spec.ts"
    tags:
      - "@maintenance"
---
# Implement Work Order Create Form (Frontend)

## Context

Work order creation form, can be opened from asset detail or maintenance list.

## Acceptance Criteria

1. Create form component `src/views/asset/maintenance/form.vue`:
   - el-drawer: 600px width
   - el-form with validation

2. Form fields:
   - Asset Selection (required if not pre-filled):
     - Search input with autocomplete
     - Shows: asset code, name, location
     - Auto-fill project from selected asset
   - Fault Type (select from dictionary, required)
   - Priority (select: normal/important/urgent, default: normal)
   - Fault Description (textarea, required)
   - Due Date (date picker)
   - Link Asset Status (checkbox, default: checked)
     - Tooltip: "When checked, asset status will change to 'Maintaining' on work order creation"
   - Attachments (el-upload)

3. Pre-fill behavior:
   - When opened from asset detail, pre-fill asset info (readonly)
   - Asset selection enabled when opened from list page

4. Form validation:
   - Asset: required
   - Fault Type: required
   - Fault Description: required, min 10 chars
   - Due Date: must be future date

5. Submit creates work order with status WAIT_ASSIGN

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 4 (Asset Form - initiate maintenance button)
