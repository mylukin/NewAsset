---
id: office.frontend-form
module: office
priority: 11
status: failing
version: 1
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - P0
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create Office Asset Form Component

## Acceptance Criteria

1. Create `/src/views/asset/office/components/OfficeForm.vue`
2. Implement common form fields:
3. Implement IT-specific fields (shown when officeType = 'IT_EQUIPMENT'):
4. Implement validation rules
5. Support create/edit modes
