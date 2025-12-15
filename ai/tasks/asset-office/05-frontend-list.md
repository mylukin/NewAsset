---
id: asset-office.frontend-list
module: asset-office
priority: 605
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.controller
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
# Implement Office Asset List Page (Frontend)

## Context

Vue page for office asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/office.js`:
   - listOffice(query)
   - getOffice(id)
   - addOffice(data)
   - updateOffice(data)
   - delOffice(ids)
   - exportOffice(query)

2. Create list page `src/views/asset/office/index.vue`:
   - Search bar: Project, office type, use department, responsible person, status
   - Action bar: New, Import, Export, Batch Delete
   - Data table:
     - Columns: Checkbox, Asset Code, Name, Type, Serial No, Use Dept, User, Status, Actions
     - Actions: View, Edit, Initiate Maintenance

3. Status filter tabs: All, In Use (Available-Self), Under Maintenance, Fault

4. Permission controls with v-hasPermi

5. Empty state message

## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
