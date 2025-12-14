---
id: parking.service
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn:
  - parking.mapper
  - core.code-generator
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Service

## Acceptance Criteria

1. Create `IParkingAssetService` interface:
2. Create `ParkingAssetServiceImpl`
3. Implement statistics:
