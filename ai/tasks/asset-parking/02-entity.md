---
id: asset-parking.entity
module: asset-parking
priority: 302
status: passing
version: 4
origin: spec-workflow
dependsOn:
  - asset-parking.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-parking/**/*.test.*
verification:
  verifiedAt: '2025-12-15T14:06:54.739Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e499ccd0873346ed8e94b0b962d3d77232381500
  summary: 5/5 criteria satisfied
---
# Create Parking Asset Entity and Mapper

## Context

Java entity class for parking extension table. Linked to base Asset entity.

## Acceptance Criteria

1. Create `AssetParking` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetParkingMapper` interface:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Similar structure to AssetHouse entity
- Reference: ai/tasks/asset-house/02-entity.md
