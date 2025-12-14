---
id: house.service
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn:
  - house.mapper
  - core.code-generator
  - core.state-machine
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/house/**/*.test.*
---
# Create House Asset Service

## Acceptance Criteria

1. Create `IHouseAssetService` interface:
2. Create `HouseAssetServiceImpl`:
3. Implement `insertHouseAsset`:
4. Implement `updateHouseAsset`:
5. Implement `deleteHouseAssetByIds`:
6. Implement `copyHouseAsset`:
7. Use `@DataScope` for project-based access control
