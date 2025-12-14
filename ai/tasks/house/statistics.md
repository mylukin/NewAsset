---
id: house.statistics
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn:
  - house.service
supersedes: []
tags:
  - backend
  - statistics
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/house/**/*.test.*
---
# Implement House Asset Statistics

## Acceptance Criteria

1. Create `HouseAssetStatistics` VO:
2. Add to `IHouseAssetService`:
3. Implement statistics query in mapper:
4. Add controller endpoint:
5. Calculate ratios in service layer (avoid division in SQL)
