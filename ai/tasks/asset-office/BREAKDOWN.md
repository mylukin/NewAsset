---
id: asset-office.BREAKDOWN
module: asset-office
priority: 6
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
  verifiedAt: '2025-12-15T08:24:46.844Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 6/6 criteria satisfied
---
# Office Asset Module Breakdown

## Module Purpose

Implements Office asset list screen for furniture and IT equipment:
- Office-specific fields (serial number, configuration)
- Health rate statistics (P1 feature, basic support in MVP)
- 1:1 extension table management with t_asset

## Scope

- Extension table `t_asset_office` with office-specific fields
- Office asset CRUD service extending base asset
- Office list with filters (type, department, responsible person, status)
- Office detail with extension fields
- Excel export for office assets

## Dependencies

- core module (base asset, code generator, status machine)
- RuoYi dictionary for office asset types (furniture, IT, other)

## Related Screens

- Office Asset List page
- Office Asset Detail drawer
- Asset Form for Office type

## Related APIs

- `GET /api/asset/office/list` - Paginated list
- `GET /api/asset/office/{id}` - Office detail
- `POST /api/asset/office` - Create office asset
- `PUT /api/asset/office/{id}` - Update office asset
- `DELETE /api/asset/office/{ids}` - Soft delete
- `POST /api/asset/office/export` - Excel export

## Test Requirements

- Unit tests: AssetOfficeServiceTest
- Integration tests: Office CRUD with base asset linkage

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/asset-office/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core module are correctly defined
4. Database schema for extension table is included
5. Office-specific service and controller tasks are included
6. Frontend list, detail, and form tasks are included
