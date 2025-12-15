---
id: asset-venue.frontend-list
module: asset-venue
priority: 505
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.controller
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
# Implement Venue Asset List Page (Frontend)

## Context

Vue page for venue asset list.

## Acceptance Criteria

1. Create API file `src/api/asset/venue.js`:
   - listVenue(query)
   - getVenue(id)
   - addVenue(data)
   - updateVenue(data)
   - delVenue(ids)
   - exportVenue(query)

2. Create list page `src/views/asset/venue/index.vue`:
   - Search bar: Project, venue type, usage mode, availability status
   - Action bar: New, Import, Export, Batch Delete
   - Data table:
     - Columns: Checkbox, Asset Code, Name, Type, Capacity, Usage Mode, Availability, Open Time, Actions
     - Actions: View, Edit, Initiate Maintenance
   - Availability status badges with colors

3. Status filter tabs: All, Available, Occupied, Maintaining

4. Permission controls with v-hasPermi

5. Empty state message

## Technical Notes

- Similar structure to house list page
- Reference: ai/tasks/asset-house/06-frontend-list.md
