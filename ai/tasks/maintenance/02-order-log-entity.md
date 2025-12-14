---
id: maintenance.order-log-entity
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order Log Entity

## Context

Log entity tracks all status changes and operations on maintenance orders for audit trail.

## Acceptance Criteria

1. Create `MaintenanceOrderLog` entity mapping `t_asset_maint_log`:
   - `id` - Long (PK)
   - `orderId` - Long (FK to t_asset_maint_order)
   - `fromStatus` - String
   - `toStatus` - String
   - `operatorId` - Long
   - `operationType` - String (MaintOperationTypeEnum)
   - `content` - String (备注内容/处理说明)
   - `opTime` - Date

2. Create `MaintenanceOrderLogVO`

3. Support attachments per log entry (optional - can store in content as JSON or use separate table)

## Technical Notes

- Reference: TECH.md section 4.2.2
