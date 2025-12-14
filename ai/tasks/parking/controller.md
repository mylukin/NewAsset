---
id: parking.controller
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn:
  - parking.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Controller

## Acceptance Criteria

1. Create `ParkingAssetController`:
2. Implement endpoints:
3. Add permission annotations:
4. Add `@Log` annotations
