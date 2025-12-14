---
id: house.frontend-form
module: house
priority: 17
status: failing
version: 15
origin: manual
dependsOn:
  - house.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Form Dialog

## Context

Vue dialog component for creating and editing house assets with form validation.

## Acceptance Criteria

1. Create form dialog component (inline in index.vue or separate)
2. Implement form fields grouped by category:
   - Basic info: assetName, projectId, building, floor, roomNo
   - Property info: buildingArea, innerArea, houseType, houseUsage
   - Ownership: ownershipType, ownerOrg, useDeptId, dutyUserId
   - Usage: currentUsage, currentUser, contractNo, rentUnitPrice, rentTotal
   - Value: originalValue, purchaseDate, startUseDate, depreciationMethod
   - Other: brand, model, supplier, warrantyExpireDate, remark
3. Show auto-generated asset code (read-only) after selecting project/type
4. Add form validation rules for required fields
5. Support both add and edit modes
6. Handle form submission with loading state
7. Reset form on dialog close

## Technical Notes

- Reference: PRD Section 6.2.2
- Pattern: el-dialog with el-form
- Validation: Use element-ui form rules
