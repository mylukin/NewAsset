---
id: parking.frontend-list
module: parking
priority: 35
status: failing
version: 3
origin: manual
dependsOn:
  - parking.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset List Page

## Context

Vue component for parking asset list.

## Acceptance Criteria

1. Create `/src/views/asset/parking/index.vue`
2. Implement search filters: project, parkingZone, parkingNo, parkingType, status
3. Data table columns:
   - Asset code, parking no, zone, type, area
   - Status, current user, plate no, rent price
   - Actions
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi

## Technical Notes

- Reference: PRD Section 6.4.2
- Pattern: RuoYi list page
