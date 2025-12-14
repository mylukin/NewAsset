---
id: maintenance.order-entity
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order Entity

## Context

Maintenance orders track repair requests, inspections, and maintenance activities for assets.

## Acceptance Criteria

1. Create `MaintenanceOrder` entity mapping `t_asset_maint_order`:
   - `id` - Long (PK)
   - `orderNo` - String (unique, auto-generated)
   - `assetId` - Long (FK to t_asset)
   - `projectId` - Long
   - `orderType` - String (故障/报修/巡检/维保)
   - `faultType` - String (dict)
   - `title` - String
   - `description` - String
   - `priority` - String (一般/重要/紧急)
   - `requireFinishTime` - Date
   - `status` - String (see MaintOrderStatusEnum)
   - `originAssetStatus` - String (asset status when order created)
   - `currentHandlerId` - Long (运维人员)
   - `requesterId` - Long (发起人)
   - Audit fields

2. Create `MaintOrderStatusEnum`:
   - `WAIT_ASSIGN` - 待派单
   - `WAIT_ACCEPT` - 待接单
   - `PROCESSING` - 处理中
   - `WAIT_CONFIRM` - 待验收
   - `CLOSED` - 已关闭
   - `CANCELED` - 已取消

3. Create `MaintOperationTypeEnum`:
   - CREATE, ASSIGN, ACCEPT, START_HANDLE, COMPLETE, CONFIRM, REJECT, CANCEL

4. Create `MaintenanceOrderVO`, DTOs

## Technical Notes

- Reference: TECH.md section 4.2.1, 5.3
