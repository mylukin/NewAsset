---
id: venue.frontend-form
module: venue
priority: 45
status: failing
version: 15
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
# Create Venue Asset Form Dialog

## Context

Vue dialog for creating and editing venue assets.

## Acceptance Criteria

1. Create form dialog component
2. Form fields:
   - Basic: assetName, projectId, building, floor, locationDesc
   - Venue: venueType, capacity, useMode, openTimeDesc, currentVenueStatus
   - Common: brand, model, supplier, originalValue, remark
3. Auto-generated asset code display
4. Form validation
5. Add and edit modes

## Technical Notes

- Reference: PRD Section 6.5.2
- Pattern: el-dialog with el-form
