---
id: asset-facility.entity
module: asset-facility
priority: 402
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.database-schema
  - core.asset-entity
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-facility/**/*.test.*"
---
# Create Facility Asset Entity and Mapper

## Context

Java entity class for facility extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetFacility` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_facility
   - @TableName annotation or XML mapping
   - Lombok or standard getters/setters

2. Create `AssetFacilityMapper` interface in `com.ruoyi.asset.mapper`:
   - selectByAssetId(Long assetId)
   - insert, update, deleteByAssetId

3. Create mapper XML file:
   - Location: `resources/mapper/asset/AssetFacilityMapper.xml`
   - ResultMap for AssetFacility entity

4. Create DTO/VO classes:
   - `AssetFacilityVO`: Combined view (base + extension fields)
   - `AssetFacilityCreateDTO`: Create request with all fields
   - `AssetFacilityUpdateDTO`: Update request
   - `AssetFacilityQueryDTO`: List query with filters

5. Entity compiles without errors

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
