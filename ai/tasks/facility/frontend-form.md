---
id: facility.frontend-form
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn:
  - facility.frontend-api
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
# Create Facility Asset Form Component

## Acceptance Criteria

1. Create `/src/views/asset/facility/components/FacilityForm.vue`
2. Implement form fields:
3. Implement validation rules
4. Support create/edit modes
