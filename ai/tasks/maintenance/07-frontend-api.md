---
id: maintenance.frontend-api
module: maintenance
priority: 63
status: failing
version: 1
origin: manual
dependsOn:
  - maintenance.order-controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Frontend API Module

## Context

Axios API module for maintenance order frontend.

## Acceptance Criteria

1. Create `/src/api/asset/maintOrder.js`
2. Implement API functions:
   - listMaintOrder, getMaintOrder, addMaintOrder, exportMaintOrder
   - assignOrder, acceptOrder, startOrder, completeOrder, confirmOrder, cancelOrder
   - getMyPendingOrders, getOrdersByAsset
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/maintOrder.js`
