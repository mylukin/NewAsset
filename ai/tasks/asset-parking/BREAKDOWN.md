---
id: asset-parking.BREAKDOWN
module: asset-parking
priority: 3
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
  verifiedAt: '2025-12-15T08:08:12.110Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 7/7 criteria satisfied
---
# Parking Asset Module Breakdown

## Module Purpose

Implements Parking asset list screen with vehicle spot management:
- Parking-specific fields (spot number, zone, vehicle info)
- Parking utilization statistics
- 1:1 extension table management with t_asset

## Scope

- Extension table `t_asset_parking` with parking-specific fields
- Parking asset CRUD service extending base asset
- Parking statistics (utilization rate by zone)
- Parking list with filters (zone, spot number, status, type)
- Parking detail with extension fields
- Excel export for parking assets

## Dependencies

- core module (base asset, code generator, status machine)
- RuoYi dictionary for parking types, zones

## Related Screens

- Parking Asset List page
- Parking Asset Detail drawer
- Asset Form for Parking type

## Related APIs

- `GET /api/asset/parking/list` - Paginated list
- `GET /api/asset/parking/{id}` - Parking detail
- `POST /api/asset/parking` - Create parking asset
- `PUT /api/asset/parking/{id}` - Update parking asset
- `DELETE /api/asset/parking/{ids}` - Soft delete
- `POST /api/asset/parking/export` - Excel export
- `GET /api/asset/parking/stats` - Utilization statistics

## Test Requirements

- Unit tests: AssetParkingServiceTest
- Integration tests: Parking CRUD with base asset linkage
- E2E tests: parking-crud.spec.ts

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/asset-parking/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for extension table is included
5. Parking-specific service and controller tasks are included
6. Statistics calculation tasks are included
7. Frontend list, detail, and form tasks are included
