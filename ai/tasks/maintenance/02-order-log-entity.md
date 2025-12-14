---
id: maintenance.order-log-entity
module: maintenance
priority: 58
status: failing
version: 4
origin: manual
dependsOn:
  - maintenance.order-entity
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Log Entity

## Context

Order logs track all workflow transitions and processing notes for maintenance orders.

## Acceptance Criteria

1. Create `AssetMaintLog` entity mapping `t_asset_maint_log`
2. Map all fields:
   - orderId (关联工单)
   - fromStatus (原状态)
   - toStatus (目标状态)
   - operatorId (操作人)
   - operationType (操作类型: 派单/接单/开始处理/完成/验收/取消)
   - content (备注/处理说明)
   - opTime (操作时间)
3. Create VO for display in timeline
4. Include related operator name for display

## Technical Notes

- Reference: TECH.md Section 4.2.2
- Pattern: Audit log entity
- Location: `com.ruoyi.asset.domain.entity.AssetMaintLog`
