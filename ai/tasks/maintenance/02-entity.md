---
id: maintenance.entity
module: maintenance
priority: 702
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.database-schema
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Work Order Entity and Mapper

## Context

Java entity classes for work order management.

## Acceptance Criteria

1. Create `MaintOrder` entity in `com.ruoyi.asset.domain.entity`:
   - Fields mapped from t_asset_maint_order
   - @TableName annotation or XML mapping
   - Lombok or standard getters/setters

2. Create `MaintLog` entity:
   - Fields mapped from t_asset_maint_log
   - Operator details for timeline display

3. Create `MaintOrderMapper` interface in `com.ruoyi.asset.mapper`:
   - selectById(Long id)
   - selectList(MaintOrderQueryDTO query) - with pagination
   - selectByAssetId(Long assetId) - for asset detail tab
   - insert, update, updateStatus
   - selectCountByStatus() - for dashboard

4. Create `MaintLogMapper` interface:
   - selectByOrderId(Long orderId)
   - insert

5. Create mapper XML files:
   - `resources/mapper/asset/MaintOrderMapper.xml`
   - `resources/mapper/asset/MaintLogMapper.xml`
   - ResultMaps with linked asset info

6. Create DTO/VO classes:
   - `MaintOrderVO`: Full view with asset info, requester/handler names
   - `MaintOrderCreateDTO`: Create request
   - `MaintOrderQueryDTO`: List query with filters
   - `MaintLogVO`: Timeline display

7. All entities compile without errors

## Technical Notes

- Join with t_asset for asset code/name in list
- Join with sys_user for requester/handler names
