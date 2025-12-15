---
id: asset-parking.frontend-list
module: asset-parking
priority: 306
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.controller
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
# Implement Parking Asset List Page (Frontend)

## Context

Vue page for parking asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/parking.js`:
   - listParking(query)
   - getParking(id)
   - addParking(data)
   - updateParking(data)
   - delParking(ids)
   - exportParking(query)
   - getParkingStats(projectId)

2. Create list page `src/views/asset/parking/index.vue`:
   - Search bar: Project, zone, parking number, status, type
   - Action bar: New, Import, Export, Batch Delete
   - Data table:
     - Columns: Checkbox, Asset Code, Parking No, Zone, Type, Status, User, Plate No, Actions
     - Actions: View, Edit, Initiate Maintenance

3. Status filter tabs: All, Self-use, Rented, Idle

4. Permission controls with v-hasPermi

5. Empty state message

## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
