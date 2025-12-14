---
id: parking.mapper
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn:
  - parking.entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Mapper

## Acceptance Criteria

1. Create `ParkingAssetMapper` interface
2. Create `ParkingAssetMapper.xml`
3. Implement query methods:
4. Implement write methods:
5. Support joined queries with base asset table
