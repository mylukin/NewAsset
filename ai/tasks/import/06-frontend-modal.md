---
id: import.frontend-modal
module: import
priority: 806
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - import.controller
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/import/**/*.spec.ts"
    tags:
      - "@import"
---
# Implement Import Modal (Frontend)

## Context

3-step import wizard modal component.

## Acceptance Criteria

1. Create API file `src/api/asset/import.js`:
   - importAssets(assetType, file, options)
   - validateAssets(assetType, file)
   - downloadTemplate(assetType)

2. Create modal component `src/views/asset/components/ImportModal.vue`:
   - el-dialog with 600px width
   - Props: assetType, visible

3. Implement 3-step wizard (el-steps):

   **Step 1: Download Template**
   - Instruction text explaining format requirements
   - "Download Template" button (primary)
   - "Next" button (enabled after download or skip)

   **Step 2: Upload File**
   - el-upload drag-drop zone
   - File type restriction (.xlsx, .xls)
   - File size display
   - Progress bar during upload
   - "Validate" button to preview errors

   **Step 3: Review Results**
   - Show validation results table
   - Columns: Row #, Column, Value, Error Message
   - Summary: X valid, Y invalid rows
   - Import options: "Import Valid Only" or "Cancel"
   - "Import" button (disabled if no valid rows)

4. Success state:
   - Show success message with imported count
   - "Close" button to dismiss
   - Emit 'success' event to refresh list

5. Error handling:
   - Network errors: retry button
   - Validation errors: display in table

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 7 (Import Modal)
- Reusable across all asset types
