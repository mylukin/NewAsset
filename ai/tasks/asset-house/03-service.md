---
id: asset-house.service
module: asset-house
priority: 203
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.entity
  - core.base-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/asset-house/**/*.test.*"
    cases:
      - "should create house asset with extension"
      - "should update house fields"
      - "should copy asset for same floor"
---
# Implement House Asset Service

## Context

Service for house asset CRUD operations. Extends base asset service with house-specific fields.

## Acceptance Criteria

1. Create `IAssetHouseService` interface in `com.ruoyi.asset.service`:
   - `AssetHouseVO create(AssetHouseCreateDTO dto)`
   - `AssetHouseVO update(AssetHouseUpdateDTO dto)`
   - `void delete(Long[] ids)`
   - `AssetHouseVO getById(Long id)`
   - `PageResult<AssetHouseVO> list(AssetHouseQueryDTO query)`
   - `AssetHouseVO copy(Long sourceId)`: Copy for same-floor batch entry

2. Create `AssetHouseServiceImpl`:
   - Inject AssetMapper, AssetHouseMapper, AssetCodeGenerator
   - Transactional operations for base + extension tables

3. Create operation:
   - Generate asset code (HA-XXXXXX)
   - Insert into t_asset with asset_type='HOUSE'
   - Insert into t_asset_house with extension fields
   - Return combined VO

4. Update operation:
   - Update t_asset base fields
   - Update t_asset_house extension fields
   - Both in same transaction

5. Copy operation:
   - Copy all fields from source asset
   - Clear: id, asset_code, room_no, current_user, contract_no
   - Generate new asset code
   - Return pre-filled VO for editing

6. Delete operation:
   - Call base service delete (checks for open work orders)
   - Soft delete both tables

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Copy asset feature)
- Transaction required for base + extension table consistency
