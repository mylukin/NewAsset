---
id: facility.frontend-form
module: facility
priority: 26
status: failing
version: 4
origin: manual
dependsOn:
  - facility.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Form Dialog

## Context

Vue dialog for creating and editing facility assets.

## Acceptance Criteria

1. Create form dialog component
2. Implement form fields:
   - Basic: assetName, projectId, building, floor, locationDesc
   - Facility: facilityType, installPosition, equipmentNo
   - Maintenance: maintOrg, maintPhone, warrantyExpireDate
   - Common: brand, model, supplier, originalValue, remark
3. Show auto-generated asset code
4. Add validation rules
5. Support add and edit modes

## Technical Notes

- Reference: PRD Section 6.3.2
- Pattern: el-dialog with el-form
