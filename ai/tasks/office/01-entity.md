---
id: office.entity
module: office
priority: 48
status: failing
version: 19
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Entity

## Context

Office assets include furniture, IT equipment, and other office items with user assignment and condition tracking.

## Acceptance Criteria

1. Create `AssetOffice` entity referencing `Asset` base
2. Map extension fields from `t_asset_office`:
   - officeType (资产类型: 家具/IT设备/其他)
   - workplaceNo (工位号/房间号)
   - useUser (使用人)
   - serialNo (序列号/资产标签号)
   - osInfo (操作系统 - IT设备)
   - configDesc (配置描述)
3. Create VO and DTO classes
4. Add validation annotations

## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_office)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetOffice`
