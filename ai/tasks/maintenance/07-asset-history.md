---
id: maintenance.asset-history
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-entity]
supersedes: []
tags: [feature, query, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Asset Maintenance History

## Context

Each asset's detail page shows its complete maintenance history. This provides traceability and helps assess asset condition.

## Acceptance Criteria

1. Backend API `GET /asset/maint/order/byAsset/{assetId}`:
   - Returns list of orders for this asset
   - Ordered by creation time descending
   - Pagination support
   - Filter by status, type, date range
2. Response includes:
   - Order number, title, type
   - Status, priority
   - Handler name, completion time
   - Result summary
3. Frontend component:
   - Used in asset detail tabs
   - Table with key columns
   - Click to open order detail
   - Filter by type and status
4. Export capability:
   - Export maintenance history to Excel
   - Include asset info header

## Technical Notes

- Integrate into all 5 asset type detail pages
- Reusable component across modules
- Consider summary statistics (total, by type)
