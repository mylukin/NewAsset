---
id: asset-house.frontend-form
module: asset-house
priority: 208
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@house"
---
# Implement House Asset Form Component (Frontend)

## Context

Create/Edit form component for house assets. Used in drawer mode from list page.

## Acceptance Criteria

1. Create form component `src/views/asset/house/form.vue`:
   - el-drawer: 800px width, from right
   - el-form with grouped sections

2. Form sections:

   **Basic Information**
   - Asset Name (required, text input)
   - Asset Code (auto-generated, display only on create, readonly on edit)
   - Status (select, from dictionary)
   - Responsible Person (select with search)
   - Use Department (select with search)

   **Location**
   - Building (cascader level 1)
   - Floor (cascader level 2)
   - Room Number (text input)
   - Location Description (textarea)

   **House Details**
   - Building Area (number input)
   - Inner Area (number input)
   - House Type (select from dictionary)
   - House Usage (select from dictionary)
   - Current Usage (select from dictionary)
   - Current User (text input)
   - Contract Number (text input)

   **Financial (optional)**
   - Rent Unit Price (number input)
   - Rent Total (number input)
   - Purchase Date (date picker)
   - Original Value (number input)

   **Attachments**
   - el-upload with drag-drop zone
   - File list with preview

3. Location cascader:
   - Uses /asset/location/tree API
   - 3 levels: Building → Floor → Unit

4. Form validation:
   - Asset Name: required
   - Building: required
   - Floor: required
   - Room Number: required
   - Areas: positive numbers

5. Footer actions:
   - Save: Submit form
   - Save & Add Another: Submit and reset for new entry
   - Cancel: Close drawer

6. On submit:
   - Show loading state
   - Call API (create or update)
   - Show success message
   - Close drawer and refresh list

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset Form)
- Uses el-form, el-cascader, el-upload components
