---
id: maintenance.asset-maint-history
module: maintenance
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-crud-service]
supersedes: []
tags: [backend, frontend, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Asset Maintenance History

## Context

Display maintenance history on asset detail pages and support filtering/export.

## Acceptance Criteria

1. Backend:
   - Add endpoint `GET /asset/{id}/maint-history` - get maintenance orders for asset
   - Support filters: orderType, status, dateRange
   - Support export

2. Frontend - Asset Detail Integration:
   - Add "Maintenance Records" tab to all asset detail pages
   - Show list of orders with columns: Order No, Type, Status, Create Time, Handler
   - Click to view order detail
   - "Initiate Maintenance" button

3. Create reusable component:
   - `/src/components/asset/MaintHistoryList.vue`
   - Props: assetId
   - Used in house/parking/facility/venue/office detail pages

## Technical Notes

- Reference: PRD section 6.1.2 point 2
