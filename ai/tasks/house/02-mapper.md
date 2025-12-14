---
id: house.mapper
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [house.entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/house/**/*.test.*"
---
# Create House Asset Mapper

## Context

The mapper provides database access for house asset CRUD operations with support for complex queries and joins.

## Acceptance Criteria

1. Create `HouseAssetMapper` interface in `com.ruoyi.asset.mapper`

2. Create `HouseAssetMapper.xml` with SQL mappings:

3. Implement query methods:
   - `selectHouseAssetList(HouseAssetQueryDTO query)` - paginated list with filters
   - `selectHouseAssetById(Long assetId)` - single house with base asset info
   - `selectHouseAssetByCode(String assetCode)` - lookup by asset code

4. Implement write methods:
   - `insertHouseAsset(HouseAsset houseAsset)` - insert extension record
   - `updateHouseAsset(HouseAsset houseAsset)` - update extension fields
   - `deleteHouseAssetByAssetId(Long assetId)` - soft delete

5. Support joined queries with `t_asset` base table:
   ```sql
   SELECT a.*, h.*
   FROM t_asset a
   LEFT JOIN t_asset_house h ON a.id = h.asset_id
   WHERE a.asset_type = 'HOUSE' AND a.del_flag = '0'
   ```

6. Include data permission placeholder `${params.dataScope}`

7. Support sorting by create_time, building_area, rent_total

## Technical Notes

- Reference: TECH.md section 6.4
- Use MyBatis XML for complex joins
