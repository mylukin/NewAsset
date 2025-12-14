---
id: maintenance.order-controller
module: maintenance
priority: 6
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-workflow-service, maintenance.order-crud-service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Create Maintenance Order Controller

## Context

RESTful API endpoints for maintenance order operations including workflow actions.

## Acceptance Criteria

1. Create `MaintenanceOrderController`:
   - Base path: `/asset/maint/order`

2. Implement CRUD endpoints:
   - `GET /asset/maint/order/list` - list with filters
   - `GET /asset/maint/order/{id}` - get detail with logs
   - `POST /asset/maint/order` - create order
   - `PUT /asset/maint/order` - update basic info
   - `GET /asset/maint/order/my-pending` - my pending orders (for ops)
   - `GET /asset/maint/order/by-asset/{assetId}` - orders for specific asset
   - `POST /asset/maint/order/export` - export to Excel

3. Implement workflow endpoints:
   - `POST /asset/maint/order/{id}/assign` - assign to handler
   - `POST /asset/maint/order/{id}/accept` - accept order
   - `POST /asset/maint/order/{id}/start` - start processing
   - `POST /asset/maint/order/{id}/complete` - complete with result
   - `POST /asset/maint/order/{id}/confirm` - confirm/verify
   - `POST /asset/maint/order/{id}/reject` - reject back to processing
   - `POST /asset/maint/order/{id}/cancel` - cancel order

4. Add permission annotations:
   - `asset:maint:list`, `asset:maint:query`
   - `asset:maint:add`, `asset:maint:edit`
   - `asset:maint:assign` - for dispatching
   - `asset:maint:process` - for technicians
   - `asset:maint:confirm` - for verification

5. Add `@Log` annotations for all operations

## Technical Notes

- Reference: TECH.md section 6.1
