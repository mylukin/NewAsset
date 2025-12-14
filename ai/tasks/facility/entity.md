---
id: facility.entity
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - entity
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Entity

## Acceptance Criteria

1. Create `FacilityAsset` entity in `com.ruoyi.asset.domain.entity`:
2. Include facility-specific fields:
3. Create `FacilityAssetVO` for API responses
4. Create `FacilityAssetCreateDTO` and `FacilityAssetUpdateDTO`
