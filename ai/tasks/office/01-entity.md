---
id: office.entity
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/office/**/*.test.*"
---
# Create Office Asset Entity

## Context

Office assets represent office equipment including furniture, IT devices, and other office items.

## Acceptance Criteria

1. Create `OfficeAsset` entity in `com.ruoyi.asset.domain.entity`:
   - Map to `t_asset_office` table (asset_id as PK/FK)

2. Include office-specific fields:
   - `officeType` (资产类型) - String (dict: office_asset_type - 家具/IT设备/其他)
   - `workplaceNo` (工位号/房间号) - String
   - `useUser` (使用人) - String
   - `serialNo` (序列号/资产标签号) - String (for IT devices)
   - `osInfo` (操作系统) - String (optional, for IT)
   - `configDesc` (配置描述) - String (optional, for IT)

3. Create `OfficeAssetVO`, `OfficeAssetCreateDTO`, `OfficeAssetUpdateDTO`

## Technical Notes

- Reference: TECH.md section 4.1.2 (t_asset_office)
