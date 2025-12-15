---
id: import.BREAKDOWN
module: import
priority: 8
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - core.BREAKDOWN
  - asset-house.BREAKDOWN
  - asset-parking.BREAKDOWN
  - asset-facility.BREAKDOWN
  - asset-venue.BREAKDOWN
  - asset-office.BREAKDOWN
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:32:36.360Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 7/7 criteria satisfied
---
# Import Module Breakdown

## Module Purpose

Implements Import Modal for Excel import functionality:
- Excel file parsing and validation
- Batch asset creation with code generation
- Import error reporting and rollback
- Template download for each asset type

## Scope

- Excel import service using EasyExcel library
- Template generation for each asset type
- Row-by-row validation with error collection
- Batch insert with transaction rollback on failure
- Import result reporting (success/error counts, error details)
- Template download endpoints

## Dependencies

- core module (base asset, code generator)
- All asset type modules (house, parking, facility, venue, office)
- EasyExcel 3.1.x library

## Related Screens

- Import Modal (3-step wizard)
- All Asset List pages (Import button)

## Related APIs

- `POST /api/asset/import/{assetType}` - Import Excel file
- `GET /api/asset/import/{assetType}/template` - Download template
- `GET /api/asset/import/{assetType}/validate` - Validate without import (optional)

## Test Requirements

- Unit tests: ExcelImportServiceTest
- Integration tests: Import with valid/invalid/duplicate data
- Performance: 1000 rows < 30s
- Edge cases: Malformed files, missing columns, oversized files

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/import/
2. Each task has specific, testable acceptance criteria
3. Task dependencies on core and asset type modules are correctly defined
4. Excel parsing and validation service tasks are included
5. Template generation tasks for each asset type are included
6. Import result and error reporting tasks are included
7. Frontend import modal tasks (3-step wizard) are included
