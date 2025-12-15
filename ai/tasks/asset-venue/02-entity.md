---
id: asset-venue.entity
module: asset-venue
priority: 502
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.database-schema
  - core.asset-entity
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-venue/**/*.test.*"
---
# Create Venue Asset Entity and Mapper

## Context

Java entity class for venue extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetVenue` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_venue
   - @TableName annotation or XML mapping
   - Lombok or standard getters/setters

2. Create `AssetVenueMapper` interface in `com.ruoyi.asset.mapper`:
   - selectByAssetId(Long assetId)
   - insert, update, deleteByAssetId

3. Create mapper XML file:
   - Location: `resources/mapper/asset/AssetVenueMapper.xml`
   - ResultMap for AssetVenue entity

4. Create DTO/VO classes:
   - `AssetVenueVO`: Combined view (base + extension fields)
   - `AssetVenueCreateDTO`: Create request with all fields
   - `AssetVenueUpdateDTO`: Update request
   - `AssetVenueQueryDTO`: List query with filters

5. Entity compiles without errors

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
