---
id: maintenance.asset-maint-history
module: maintenance
priority: 67
status: failing
version: 14
origin: manual
dependsOn:
  - maintenance.order-crud-service
supersedes: []
tags:
  - integration
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Integrate Maintenance History into Asset Detail Pages

## Context

Each asset detail page should display its maintenance history from the maintenance module.

## Acceptance Criteria

1. Create reusable component: `AssetMaintHistory.vue`
2. Accept assetId as prop
3. Display list of related maintenance orders:
   - Order no, type, status, create time
   - Brief description, handler
4. Support filtering by status and type
5. Link to full order detail
6. Support Excel export of history
7. Integrate into all asset detail drawers:
   - house, parking, facility, venue, office

## Technical Notes

- Reference: PRD Section 6.1.2 Point 2
- Pattern: Reusable component
- API: GET /asset/maint/order/asset/{assetId}
