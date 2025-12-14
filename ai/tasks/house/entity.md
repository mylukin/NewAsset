---
id: house.entity
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - entity
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/house/**/*.test.*
---
# Create House Asset Entity

## Acceptance Criteria

1. Create `HouseAsset` entity in `com.ruoyi.asset.domain.entity`:
2. Include house-specific fields:
3. Add validation annotations:
4. Create `HouseAssetVO` for API responses (combined view with base asset)
5. Create `HouseAssetCreateDTO` and `HouseAssetUpdateDTO` for API requests
