---
id: venue.frontend-list
module: venue
priority: 44
status: failing
version: 21
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset List Page

## Context

Vue component for venue asset list.

## Acceptance Criteria

1. Create `/src/views/asset/venue/index.vue`
2. Implement search filters: project, venueType, currentVenueStatus, status
3. Data table columns:
   - Asset code, venue name, project
   - Venue type, capacity, use mode
   - Open time, current status
   - Actions
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi

## Technical Notes

- Reference: PRD Section 6.5.2
- Pattern: RuoYi list page
