---
id: core.asset-entity
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.database-schema]
supersedes: []
tags: [domain, entity]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Base Asset Entity and Domain Objects

## Context

Define the core asset entity class that maps to `t_asset` table, along with common enums, value objects, and DTOs used across all asset modules.

## Acceptance Criteria

1. Entity class `Asset` created with all fields from `t_asset`:
   - id, assetCode, assetName, assetType
   - projectId, building, floor, roomNo, locationDesc
   - ownershipType, ownerOrg, useDeptId, dutyUserId
   - status (unified state machine)
   - purchaseDate, startUseDate, originalValue
   - depreciationMethod, depreciationAmount, netValue
   - brand, model, supplier, warrantyExpireDate
   - remark, audit fields
2. `AssetTypeEnum` created: HOUSE, PARKING, FACILITY, VENUE, OFFICE
3. `AssetStatusEnum` created with all states:
   - UNDER_CONSTRUCTION, AVAILABLE_SELF, AVAILABLE_RENT
   - AVAILABLE_IDLE, TEMP_CLOSED, FAULT
   - MAINTAINING, TO_BE_SCRAPPED, SCRAPPED
4. Base VO classes created for list/detail responses
5. Base DTO classes created for create/update requests
6. MyBatis Mapper interface `AssetMapper` created

## Technical Notes

- Use Lombok annotations (@Data, @Builder, etc.)
- Follow RuoYi BaseEntity pattern for audit fields
- Reference: com.ruoyi.common.core.domain.BaseEntity
