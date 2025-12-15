---
id: asset-venue.frontend-form
module: asset-venue
priority: 507
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@venue"
---
# Implement Venue Asset Form Component (Frontend)

## Context

Create/Edit form component for venue assets.

## Acceptance Criteria

1. Create form component `src/views/asset/venue/form.vue`:
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

   **Venue Details**
   - Venue Type (select from dictionary, required)
   - Capacity (number input)
   - Usage Mode (select from dictionary)
   - Availability Status (select)
   - Open Time Description (textarea)

   **Attachments**
   - el-upload zone

3. Form validation:
   - Asset Name: required
   - Venue Type: required
   - Capacity: positive integer

4. Footer: Save, Save & Add Another, Cancel

## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
