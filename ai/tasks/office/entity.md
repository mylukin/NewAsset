---
id: office.entity
module: office
priority: 10
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
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Entity

## Acceptance Criteria

1. Create `OfficeAsset` entity in `com.ruoyi.asset.domain.entity`:
2. Include office-specific fields:
3. Create `OfficeAssetVO`, `OfficeAssetCreateDTO`, `OfficeAssetUpdateDTO`
