---
id: maintenance.BREAKDOWN
module: maintenance
priority: 7
status: failing
version: 5
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:29:06.594Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 8/8 criteria satisfied
---
# Maintenance Module Breakdown

## Module Purpose

Implements Work Order List and Work Order Detail screens with full workflow:
- Work order lifecycle management (6-state flow)
- Work order number generation (WO-20240115-001 format)
- Asset status linkage on work order transitions
- Work order log/history tracking
- Manual assignment workflow

## Scope

- Work order table `t_asset_maint_order`
- Work order log table `t_asset_maint_log`
- Work order number generator (date-based sequence)
- 6-state status machine: WAIT_ASSIGN → WAIT_ACCEPT → PROCESSING → WAIT_CONFIRM → CLOSED/CANCELED
- Status linkage service (optional checkbox, default checked)
- Work order CRUD with role-based operations
- Work order timeline/log display
- Work order attachment support

## Dependencies

- core module (base asset, status machine, attachments)
- RuoYi user system (assignment, requester)

## Related Screens

- Work Order List page (with view tabs: All, My Created, Assigned to Me)
- Work Order Detail page (timeline, action panel)
- Asset Detail drawer (Tab 2: Maintenance Records)
- Dashboard (pending work orders count)

## Related APIs

- `GET /api/asset/maint/order/list` - Paginated list with filters
- `GET /api/asset/maint/order/{id}` - Work order detail with timeline
- `POST /api/asset/maint/order` - Create work order (with linkage option)
- `PUT /api/asset/maint/order/{id}/assign` - Assign to handler
- `PUT /api/asset/maint/order/{id}/accept` - Handler accepts
- `PUT /api/asset/maint/order/{id}/complete` - Handler completes
- `PUT /api/asset/maint/order/{id}/confirm` - Requester verifies/rejects
- `PUT /api/asset/maint/order/{id}/cancel` - Cancel work order
- `GET /api/asset/maint/order/log/{orderId}` - Get work order log

## Test Requirements

- Unit tests: MaintOrderStatusMachineTest, MaintOrderServiceTest, StatusLinkageServiceTest
- Integration tests: Full work order lifecycle, status linkage
- E2E tests: work-order-lifecycle.spec.ts, status-linkage.spec.ts
- Performance: Work order creation < 2s under 30 concurrent

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/maintenance/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for work order and log tables is included
5. Work order number generator task is included
6. 6-state status machine with validation is included
7. Status linkage service tasks are included
8. Frontend list, detail, timeline, and action panel tasks are included
