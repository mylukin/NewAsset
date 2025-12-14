---
id: maintenance.frontend-form
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.frontend-api
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
# Create Maintenance Order Form Component

## Acceptance Criteria

1. Create `/src/views/asset/maint/components/OrderForm.vue`
2. Implement form fields:
3. Implement asset selector component:
4. Implement validation rules
5. Support mode when opened from asset detail (asset pre-selected)
