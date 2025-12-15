---
id: asset-office.frontend-form
module: asset-office
priority: 607
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@office"
---
# Implement Office Asset Form Component (Frontend)

## Context

Create/Edit form component for office assets.

## Acceptance Criteria

1. Create form component `src/views/asset/office/form.vue`:
   - el-drawer: 800px width
   - el-form with grouped sections

2. Form sections:

   **Basic Information**
   - Asset Name (required)
   - Asset Code (auto-generated, readonly)
   - Status (select)
   - Responsible Person (select)

   **Office Details**
   - Office Type (select from dictionary, required)
   - Serial Number (text - for IT equipment)
   - Configuration (textarea - for IT equipment specs)

   **Assignment**
   - Use Department (select from department tree)
   - User Name (text)

   **Financial**
   - Purchase Date (date picker)
   - Original Value (number input)
   - Warranty Expire Date (date picker)

   **Attachments**
   - el-upload zone

3. Form validation:
   - Asset Name: required
   - Office Type: required

4. Footer: Save, Save & Add Another, Cancel

## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
