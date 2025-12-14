---
id: parking.service
module: parking
priority: 31
status: failing
version: 21
origin: manual
dependsOn:
  - parking.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Service

## Context

Service layer for parking asset business logic.

## Acceptance Criteria

1. Create `IAssetParkingService` interface
2. Create `AssetParkingServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations

## Technical Notes

- Reference: PRD Section 6.4
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetParkingServiceImpl`
