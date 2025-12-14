---
id: house.mapper
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn:
  - house.entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/house/**/*.test.*
---
# Create House Asset Mapper

## Acceptance Criteria

1. Create `HouseAssetMapper` interface in `com.ruoyi.asset.mapper`
2. Create `HouseAssetMapper.xml` with SQL mappings:
3. Implement query methods:
4. Implement write methods:
5. Support joined queries with `t_asset` base table:
6. Include data permission placeholder `${params.dataScope}`
7. Support sorting by create_time, building_area, rent_total
