---
id: asset-facility.frontend-form
module: asset-facility
priority: 408
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@facility"
---
# Implement Facility Asset Form Component (Frontend)

## Context

Create/Edit form component for facility assets.

## Acceptance Criteria

1. Create form component `src/views/asset/facility/form.vue`:
   - el-drawer: 800px width
   - el-form with grouped sections

2. Form sections:

   **Basic Information**
   - Asset Name (required)
   - Asset Code (auto-generated, readonly)
   - Status (select)
   - Responsible Person (select)

   **Location**
   - Building (cascader from location hierarchy)
   - Floor (cascader)
   - Install Location Description (textarea)

   **Facility Details**
   - Facility Type (select from dictionary, required)
   - Device Code (text - manufacturer code)

   **Warranty & Maintenance**
   - Warranty Expire Date (date picker)
   - Maintenance Org (text)
   - Maintenance Phone (text)

   **Attachments**
   - el-upload zone

3. Form validation:
   - Asset Name: required
   - Facility Type: required
   - Maintenance Phone: phone format validation

4. Footer: Save, Save & Add Another, Cancel

## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
