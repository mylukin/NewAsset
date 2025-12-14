---
id: office.frontend-form
module: office
priority: 54
status: failing
version: 3
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Form Dialog

## Context

Vue dialog for creating and editing office assets.

## Acceptance Criteria

1. Create form dialog component
2. Form fields:
   - Basic: assetName, projectId, useDeptId, dutyUserId
   - Office: officeType, workplaceNo, useUser, serialNo
   - IT-specific: osInfo, configDesc (show when officeType is IT设备)
   - Common: brand, model, supplier, originalValue, remark
3. Conditional field display based on officeType
4. Auto-generated asset code display
5. Form validation

## Technical Notes

- Reference: PRD Section 6.6.2
- Pattern: el-dialog with el-form
- Conditional: v-if for IT-specific fields
