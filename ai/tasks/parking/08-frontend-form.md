---
id: parking.frontend-form
module: parking
priority: 36
status: failing
version: 4
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
# Create Parking Asset Form Dialog

## Context

Vue dialog for creating and editing parking assets.

## Acceptance Criteria

1. Create form dialog component
2. Form fields:
   - Basic: assetName, projectId, parkingZone
   - Parking: parkingNo, parkingType, area
   - Usage: currentUser, plateNo, rentPrice, contractNo
   - Common: brand, model, supplier, originalValue, remark
3. Auto-generated asset code display
4. Form validation
5. Add and edit modes

## Technical Notes

- Reference: PRD Section 6.4.2
- Pattern: el-dialog with el-form
