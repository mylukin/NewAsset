---
id: maintenance.order-controller
module: maintenance
priority: 62
status: failing
version: 8
origin: manual
dependsOn:
  - maintenance.order-crud-service
  - maintenance.order-workflow-service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Create Maintenance Order Controller

## Context

RESTful API controller for maintenance order management and workflow actions.

## Acceptance Criteria

1. Create `AssetMaintOrderController`
2. Implement CRUD endpoints:
   - GET /asset/maint/order/list
   - GET /asset/maint/order/{id}
   - POST /asset/maint/order (create)
   - POST /asset/maint/order/export
3. Implement workflow endpoints:
   - POST /asset/maint/order/{id}/assign - assign handler
   - POST /asset/maint/order/{id}/accept - accept order
   - POST /asset/maint/order/{id}/start - start processing
   - POST /asset/maint/order/{id}/complete - submit completion
   - POST /asset/maint/order/{id}/confirm - verify/reject
   - POST /asset/maint/order/{id}/cancel - cancel order
4. Implement query endpoints:
   - GET /asset/maint/order/my-pending - my work queue
   - GET /asset/maint/order/asset/{assetId} - orders by asset
5. Add permission annotations: `asset:maint:*`
6. Add operation logging

## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:maint:list, add, assign, accept, confirm, cancel
