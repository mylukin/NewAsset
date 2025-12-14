---
id: parking.statistics
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
  - statistics
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/parking/**/*.test.*
---
# Implement Parking Asset Statistics

## Acceptance Criteria

1. Create `ParkingAssetStatistics` VO:
2. Implement statistics query in mapper:
3. Support filtering by project and zone
4. Cache statistics for 5 minutes
