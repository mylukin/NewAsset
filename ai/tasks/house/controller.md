---
id: house.controller
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn:
  - house.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/house/**/*.test.*
---
# Create House Asset Controller

## Acceptance Criteria

1. Create `HouseAssetController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add request validation using `@Validated`
