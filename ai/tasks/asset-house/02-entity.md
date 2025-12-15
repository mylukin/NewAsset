---
id: asset-house.entity
module: asset-house
priority: 202
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.database-schema
  - core.asset-entity
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-house/**/*.test.*"
---
# Create House Asset Entity and Mapper

## Context

Java entity class for house extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetHouse` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_house
   - @TableName annotation or XML mapping
   - Lombok or standard getters/setters

2. Create `AssetHouseMapper` interface in `com.ruoyi.asset.mapper`:
   - selectByAssetId(Long assetId)
   - insert, update, deleteByAssetId

3. Create mapper XML file:
   - Location: `resources/mapper/asset/AssetHouseMapper.xml`
   - ResultMap for AssetHouse entity

4. Create DTO/VO classes:
   - `AssetHouseVO`: Combined view (base + extension fields)
   - `AssetHouseCreateDTO`: Create request with all fields
   - `AssetHouseUpdateDTO`: Update request

5. Entity compiles without errors

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
