---
id: maintenance.frontend-detail
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
# Create Maintenance Order Detail Page

## Acceptance Criteria

1. Create `/src/views/asset/maint/orderDetail.vue`
2. Implement order info header:
3. Implement timeline/log section:
4. Implement action panel (based on status):
5. Implement asset info panel:
6. Cancel button (when applicable)
