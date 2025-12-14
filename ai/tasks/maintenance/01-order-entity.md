---
id: maintenance.order-entity
module: maintenance
priority: 57
status: failing
version: 20
origin: manual
dependsOn:
  - core.database-schema
  - core.asset-status-enum
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Entity

## Context

Maintenance orders track repair requests and work orders linked to assets with workflow status tracking.

## Acceptance Criteria

1. Create `AssetMaintOrder` entity mapping `t_asset_maint_order`
2. Map all fields:
   - orderNo (工单编号 - 唯一)
   - assetId (关联资产)
   - projectId (项目)
   - orderType (类型: 故障/报修/巡检/维保)
   - faultType (故障类型)
   - title (标题)
   - description (描述)
   - priority (紧急程度: 一般/重要/紧急)
   - requireFinishTime (要求完成时间)
   - status (工单状态)
   - originAssetStatus (发起时资产状态)
   - currentHandlerId (当前处理人)
   - requesterId (发起人)
3. Create VO for list and detail views
4. Create DTO for create operations
5. Extend BaseEntity for audit fields

## Technical Notes

- Reference: TECH.md Section 4.2.1
- Pattern: Entity with status workflow
- Location: `com.ruoyi.asset.domain.entity.AssetMaintOrder`
