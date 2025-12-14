---
id: parking.entity
module: parking
priority: 8
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
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Entity

## Acceptance Criteria

1. Create `ParkingAsset` entity in `com.ruoyi.asset.domain.entity`:
2. Include parking-specific fields:
3. Create `ParkingAssetVO`, `ParkingAssetCreateDTO`, `ParkingAssetUpdateDTO`
