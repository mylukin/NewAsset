---
id: maintenance.frontend-api
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Maintenance Order Frontend API Module

## Context

Frontend API module for maintenance order operations including workflow actions.

## Acceptance Criteria

1. Create `/src/api/asset/maintOrder.js`

2. Implement CRUD API functions:
   ```javascript
   listOrder(query)
   getOrder(id)
   addOrder(data)
   updateOrder(data)
   getMyPendingOrders()
   getOrdersByAsset(assetId)
   exportOrder(query)
   ```

3. Implement workflow API functions:
   ```javascript
   assignOrder(id, handlerId)
   acceptOrder(id)
   startOrder(id)
   completeOrder(id, data) // data: { result, attachments }
   confirmOrder(id, data) // data: { accepted, reason }
   rejectOrder(id, reason)
   cancelOrder(id, reason)
   ```

## Technical Notes

- Reference: TECH.md section 7.3
