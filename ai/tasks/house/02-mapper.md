---
id: house.mapper
module: house
priority: 11
status: failing
version: 7
origin: manual
dependsOn:
  - house.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Mapper

## Context

MyBatis mapper for house asset CRUD operations, joining base asset table with house extension table.

## Acceptance Criteria

1. Create `AssetHouseMapper` interface in `com.ruoyi.asset.mapper`
2. Create corresponding XML mapper file
3. Implement methods:
   - `selectAssetHouseList(AssetHouse query)` - paginated list with filters
   - `selectAssetHouseById(Long id)` - detail with joined base asset
   - `insertAssetHouse(AssetHouse house)` - insert both base and extension
   - `updateAssetHouse(AssetHouse house)` - update both tables
   - `deleteAssetHouseByIds(Long[] ids)` - soft delete
4. Support data scope filtering via `${params.dataScope}`
5. Include filters: projectId, building, floor, status, houseType, currentUsage, areaRange
6. Join query to include asset base fields

## Technical Notes

- Reference: TECH.md Section 6.4
- Pattern: MyBatis XML mapper with join queries
- Location: `resources/mapper/asset/AssetHouseMapper.xml`
