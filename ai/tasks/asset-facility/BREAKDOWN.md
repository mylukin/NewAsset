---
id: asset-facility.BREAKDOWN
module: asset-facility
priority: 4
status: failing
version: 3
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:18:13.000Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 6/6 criteria satisfied
---
# Facility Asset Module Breakdown

## Module Purpose

Implements Residential Facility list screen for building equipment:
- Facility-specific fields (equipment type, maintenance org)
- Warranty expiration tracking
- 1:1 extension table management with t_asset

## Scope

- Extension table `t_asset_facility` with facility-specific fields
- Facility asset CRUD service extending base asset
- Facility list with filters (type, building, floor, status)
- Facility detail with extension fields and maintenance records
- Excel export for facility assets

## Dependencies

- core module (base asset, code generator, status machine)
- RuoYi dictionary for facility types

## Related Screens

- Facility Asset List page
- Facility Asset Detail drawer
- Asset Form for Facility type

## Related APIs

- `GET /api/asset/facility/list` - Paginated list
- `GET /api/asset/facility/{id}` - Facility detail
- `POST /api/asset/facility` - Create facility asset
- `PUT /api/asset/facility/{id}` - Update facility asset
- `DELETE /api/asset/facility/{ids}` - Soft delete
- `POST /api/asset/facility/export` - Excel export

## Test Requirements

- Unit tests: AssetFacilityServiceTest
- Integration tests: Facility CRUD with base asset linkage

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/asset-facility/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for extension table is included
5. Facility-specific service and controller tasks are included
6. Frontend list, detail, and form tasks are included
