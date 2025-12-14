---
id: core.asset-base-entity
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Create Base Asset Entity

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`
2. Map all fields from `t_asset` table:
3. Extend RuoYi's `BaseEntity` for audit fields
4. Add proper validation annotations (@NotBlank, @Size, etc.)
5. Create `AssetTypeEnum` enum for asset types (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
6. Create `OwnershipTypeEnum` for ownership types
7. Override `toString()` for logging purposes
