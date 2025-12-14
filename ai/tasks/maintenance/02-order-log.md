---
id: maintenance.order-log
module: maintenance
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity]
supersedes: []
tags: [domain, entity]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Maintenance Order Log Entity

## Context

Order logs record every status change and operation on work orders, forming an audit trail and timeline view.

## Acceptance Criteria

1. `MaintOrderLog` entity created for `t_asset_maint_log`:
   - id, orderId (FK)
   - fromStatus, toStatus
   - operatorId
   - operationType (CREATE, ASSIGN, ACCEPT, COMPLETE, etc.)
   - content (remarks, notes)
   - opTime
2. `MaintOrderLogVO` for timeline display:
   - Operator name
   - Operation description
   - Time formatted
3. `MaintOrderLogMapper`:
   - insertLog(entity)
   - selectLogsByOrderId(orderId) - ordered by time
4. Service method `addLog()`:
   - Auto-capture operator from security context
   - Auto-set operation time

## Technical Notes

- Immutable records (append-only)
- Display as timeline in order detail
- Consider attachment links in content (JSON)
