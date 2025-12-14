---
id: parking.service
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [parking.mapper, core.code-generator]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/parking/**/*.test.*"
---
# Create Parking Asset Service

## Context

Business logic for parking assets including statistics.

## Acceptance Criteria

1. Create `IParkingAssetService` interface:
   - Standard CRUD methods
   - `ParkingAssetStatistics getStatistics(Long projectId, String parkingZone)`
   - `List<ParkingAssetStatistics> getStatisticsByProject()`

2. Create `ParkingAssetServiceImpl`

3. Implement statistics:
   - Total parking count
   - Self-use count/ratio
   - Rented count/ratio
   - Idle count/ratio
   - Group by project and zone

## Technical Notes

- Reference: PRD section 6.4.2 point 4
