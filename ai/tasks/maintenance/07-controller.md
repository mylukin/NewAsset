---
id: maintenance.controller
module: maintenance
priority: 707
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.order-service
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Work Order REST API

## Context

REST controller for work order operations.

## Acceptance Criteria

1. Create `MaintOrderController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/maint/order`

2. Implement endpoints:
   - `GET /list` - Paginated list with filters
   - `GET /{id}` - Get work order detail with timeline
   - `POST /` - Create work order
   - `PUT /{id}/assign` - Assign to handler (manager only)
   - `PUT /{id}/accept` - Accept work order (handler only)
   - `PUT /{id}/complete` - Complete work order (handler only)
   - `PUT /{id}/confirm` - Verify and close/reject (requester/manager)
   - `PUT /{id}/cancel` - Cancel work order
   - `GET /log/{orderId}` - Get status transition log
   - `GET /by-asset/{assetId}` - Work orders for asset

3. Add permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:maint:list')")
   - @PreAuthorize("@ss.hasPermi('asset:maint:add')")
   - @PreAuthorize("@ss.hasPermi('asset:maint:assign')")
   - @PreAuthorize("@ss.hasPermi('asset:maint:process')")
   - @PreAuthorize("@ss.hasPermi('asset:maint:confirm')")

4. Add @Log annotations for all write operations

5. Request validation with @Validated

6. Role-based operation validation:
   - Only manager can assign
   - Only assigned handler can accept/complete
   - Only requester/manager can confirm

## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level permissions
