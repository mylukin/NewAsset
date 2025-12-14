---
id: house.frontend-form
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn:
  - house.frontend-api
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
# Create House Asset Form Component

## Acceptance Criteria

1. Create `/src/views/asset/house/components/HouseForm.vue`:
2. Implement form with all fields:
3. Implement validation rules:
4. Support edit mode - load existing data
5. Display generated asset code after save
6. Emit save/cancel events to parent
