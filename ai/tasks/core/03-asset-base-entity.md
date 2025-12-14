---
id: core.asset-base-entity
module: core
priority: 3
status: failing
version: 2
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Create Base Asset Entity and Common Fields

## Context

All asset types share common fields defined in `t_asset`. A base entity class provides reusable field definitions and mapping for all asset types.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`
2. Map all common fields: id, assetCode, assetName, assetType, projectId, building, floor, roomNo, locationDesc
3. Map ownership fields: ownershipType, ownerOrg, useDeptId, dutyUserId
4. Map value fields: purchaseDate, startUseDate, originalValue, depreciationMethod, depreciationAmount, netValue
5. Map supplier fields: brand, model, supplier, warrantyExpireDate
6. Include status field with proper enum mapping
7. Extend RuoYi `BaseEntity` for audit fields
8. Add proper validation annotations (@NotBlank, @Size, etc.)

## Technical Notes

- Reference: TECH.md Section 4.1.1
- Pattern: JPA/MyBatis entity with Lombok
- File: `com.ruoyi.asset.domain.entity.Asset`
