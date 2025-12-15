---
id: asset-facility.entity
module: asset-facility
priority: 402
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-facility/**/*.test.*
---
# Create Facility Asset Entity and Mapper

## Context

Java entity class for facility extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetFacility` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetFacilityMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
