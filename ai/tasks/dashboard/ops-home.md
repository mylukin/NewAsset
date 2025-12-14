---
id: dashboard.ops-home
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn:
  - dashboard.frontend-api
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
# Create Operations Staff Dashboard Page

## Acceptance Criteria

1. Create `/src/views/dashboard/OpsHome.vue`
2. Implement "My Pending Orders" section:
3. Implement my statistics cards:
4. Implement optional:
5. Highlight overdue items in red
