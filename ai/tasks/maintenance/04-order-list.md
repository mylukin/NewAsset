---
id: maintenance.order-list
module: maintenance
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Maintenance Order List

## Context

The order list is the main interface for managing work orders. Different roles see different views based on their responsibilities.

## Acceptance Criteria

1. Backend API `GET /asset/maint/order/list`:
   - Query params: projectId, assetType, status, priority, handlerId, requesterId, keyword, dateRange
   - Pagination support
   - Data permission applied
   - Returns paginated MaintOrderVO list
2. Role-based filtering:
   - Operations staff: see assigned orders
   - Asset manager: see orders for their assets
   - Management: see all (read-only)
3. Frontend page `views/asset/maint/orderList.vue`:
   - Filter tabs: All, My Pending, My Processing
   - Search form with filters
   - Data table columns:
     - Order number, title
     - Asset code, asset name
     - Project, priority, status
     - Handler, requester
     - Created time, required finish time
   - Row actions based on status:
     - WAIT_ASSIGN: Assign
     - WAIT_ACCEPT: Accept
     - PROCESSING: Complete
     - WAIT_CONFIRM: Confirm
4. Toolbar buttons:
   - Create new order
   - Export Excel
5. Status badge with colors

## Technical Notes

- Join with t_asset for asset details
- Priority affects sorting
- Overdue orders highlighted
