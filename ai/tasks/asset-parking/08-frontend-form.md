---
id: asset-parking.frontend-form
module: asset-parking
priority: 308
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@parking"
---
# Implement Parking Asset Form Component (Frontend)

## Context

Create/Edit form component for parking assets.

## Acceptance Criteria

1. Create form component `src/views/asset/parking/form.vue`:
   - el-drawer: 800px width
   - el-form with grouped sections

2. Form sections:

   **Basic Information**
   - Asset Name (required)
   - Asset Code (auto-generated, readonly)
   - Status (select)
   - Responsible Person (select)

   **Parking Details**
   - Parking Zone (select from dictionary)
   - Parking Number (required)
   - Parking Type (select from dictionary)
   - Area (number input)

   **Usage Info**
   - Current User (text)
   - Plate Number (text)
   - Contract Number (text)
   - Rent Price (number)

   **Attachments**
   - el-upload zone

3. Form validation:
   - Parking Number: required
   - Zone: required

4. Footer: Save, Save & Add Another, Cancel

## Technical Notes

- Similar structure to house form
- Reference: ai/tasks/asset-house/08-frontend-form.md
