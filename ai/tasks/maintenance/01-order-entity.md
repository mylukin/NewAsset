---
id: maintenance.order-entity
module: maintenance
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [domain, entity]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Maintenance Work Order Entity

## Context

Maintenance work orders track all repair and maintenance activities for assets. They have their own status workflow and link to assets.

## Acceptance Criteria

1. `MaintOrder` entity created for `t_asset_maint_order`:
   - id, orderNo (auto-generated)
   - assetId (FK to t_asset)
   - projectId
   - orderType (fault, repair, inspection)
   - faultType (electrical, plumbing, etc.)
   - title, description
   - priority (normal, important, urgent)
   - requireFinishTime
   - status (workflow states)
   - originAssetStatus (saved before change)
   - currentHandlerId (assigned technician)
   - requesterId (who created it)
   - Audit fields
2. `MaintOrderStatusEnum`:
   - WAIT_ASSIGN, WAIT_ACCEPT, PROCESSING
   - WAIT_CONFIRM, CLOSED, CANCELED
3. `MaintOperationType` enum for log operations
4. `MaintOrderVO`, `MaintOrderCreateDTO`, `MaintOrderUpdateDTO`
5. `MaintOrderMapper` with CRUD and status queries

## Technical Notes

- orderNo format: MO-{yyyyMMdd}-{seq}
- Link to asset for code and location display
- Status transitions have rules
