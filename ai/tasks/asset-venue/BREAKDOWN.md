---
id: asset-venue.BREAKDOWN
module: asset-venue
priority: 5
status: passing
version: 4
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:21:31.165Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 6/6 criteria satisfied
---
# Venue Asset Module Breakdown

## Module Purpose

Implements Venue asset list screen for community spaces:
- Venue-specific fields (capacity, usage mode, schedule)
- Venue availability status
- 1:1 extension table management with t_asset

## Scope

- Extension table `t_asset_venue` with venue-specific fields
- Venue asset CRUD service extending base asset
- Venue list with filters (type, project, status)
- Venue detail with extension fields and availability info
- Excel export for venue assets

## Dependencies

- core module (base asset, code generator, status machine)
- RuoYi dictionary for venue types, usage modes

## Related Screens

- Venue Asset List page
- Venue Asset Detail drawer
- Asset Form for Venue type

## Related APIs

- `GET /api/asset/venue/list` - Paginated list
- `GET /api/asset/venue/{id}` - Venue detail
- `POST /api/asset/venue` - Create venue asset
- `PUT /api/asset/venue/{id}` - Update venue asset
- `DELETE /api/asset/venue/{ids}` - Soft delete
- `POST /api/asset/venue/export` - Excel export

## Test Requirements

- Unit tests: AssetVenueServiceTest
- Integration tests: Venue CRUD with base asset linkage

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/asset-venue/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for extension table is included
5. Venue-specific service and controller tasks are included
6. Frontend list, detail, and form tasks are included
