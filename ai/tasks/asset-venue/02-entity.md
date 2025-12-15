---
id: asset-venue.entity
module: asset-venue
priority: 502
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-venue.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-venue/**/*.test.*
---
# Create Venue Asset Entity and Mapper

## Context

Java entity class for venue extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetVenue` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetVenueMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
