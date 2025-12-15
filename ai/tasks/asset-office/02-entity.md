---
id: asset-office.entity
module: asset-office
priority: 602
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.database-schema
  - core.asset-entity
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-office/**/*.test.*"
---
# Create Office Asset Entity and Mapper

## Context

Java entity class for office extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetOffice` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_office
   - @TableName annotation or XML mapping
   - Lombok or standard getters/setters

2. Create `AssetOfficeMapper` interface in `com.ruoyi.asset.mapper`:
   - selectByAssetId(Long assetId)
   - insert, update, deleteByAssetId

3. Create mapper XML file:
   - Location: `resources/mapper/asset/AssetOfficeMapper.xml`
   - ResultMap for AssetOffice entity

4. Create DTO/VO classes:
   - `AssetOfficeVO`: Combined view (base + extension fields)
   - `AssetOfficeCreateDTO`: Create request with all fields
   - `AssetOfficeUpdateDTO`: Update request
   - `AssetOfficeQueryDTO`: List query with filters

5. Entity compiles without errors

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
