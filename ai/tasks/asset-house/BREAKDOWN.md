---
id: asset-house.BREAKDOWN
module: asset-house
priority: 2
status: passing
version: 5
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:04:41.203Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 7/7 criteria satisfied
---
# House Asset Module Breakdown

## Module Purpose

Implements House asset list screen with extension fields for residential properties:
- House-specific fields (area, house type, rental info)
- House statistics (vacancy rate, rental rate)
- 1:1 extension table management with t_asset

## Scope

- Extension table `t_asset_house` with house-specific fields
- House asset CRUD service extending base asset
- House statistics calculation (vacancy rate, rental rate)
- House list with filters (building, floor, room, status, area range)
- House detail with extension fields
- Copy asset feature for same-floor batch registration
- Excel export for house assets

## Dependencies

- core module (base asset, code generator, status machine)
- RuoYi dictionary for house types, usage types

## Related Screens

- House Asset List page
- House Asset Detail drawer (Tab 1: Basic Info + House fields)
- Asset Form for House type

## Related APIs

- `GET /api/asset/house/list` - Paginated list with filters
- `GET /api/asset/house/{id}` - House detail with extensions
- `POST /api/asset/house` - Create house asset
- `PUT /api/asset/house/{id}` - Update house asset
- `DELETE /api/asset/house/{ids}` - Soft delete
- `POST /api/asset/house/export` - Excel export
- `GET /api/asset/house/stats` - Vacancy/rental statistics

## Test Requirements

- Unit tests: AssetHouseServiceTest
- Integration tests: House CRUD with base asset linkage
- E2E tests: house-crud.spec.ts

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/asset-house/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for extension table is included
5. House-specific service and controller tasks are included
6. Statistics calculation tasks are included
7. Frontend list, detail, and form tasks are included
