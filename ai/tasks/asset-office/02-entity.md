---
id: asset-office.entity
module: asset-office
priority: 602
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - asset-office.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-office/**/*.test.*
---
# Create Office Asset Entity and Mapper

## Context

Java entity class for office extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetOffice` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetOfficeMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
