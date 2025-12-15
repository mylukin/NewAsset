---
id: core.BREAKDOWN
module: core
priority: 1
status: failing
version: 6
origin: spec-workflow
dependsOn: []
supersedes: []
tags:
  - breakdown
  - spec-generated
verification:
  verifiedAt: '2025-12-15T08:00:42.525Z'
  verdict: pass
  verifiedBy: claude
  commitHash: unknown
  summary: 7/7 criteria satisfied
---
# Core Module Breakdown

## Module Purpose

Implements shared asset functionality across all 5 asset type screens:
- Base asset entity and CRUD operations
- Asset code generation (HA-000001 format, global sequence)
- Unified 9-state status machine with transition validation
- Attachment management (10 files, 5MB each)
- 3-level location hierarchy (Building → Floor → Unit)
- Project-scoped data permission filtering

## Scope

- Base entity `t_asset` with common fields
- Location hierarchy table `t_asset_location`
- Asset code sequence table `t_asset_code_seq`
- Attachment table `t_asset_attachment`
- Status enum and transition validation
- Soft delete support
- Change logging for audit

## Dependencies

- RuoYi system module (sys_user, sys_dept, sys_dict)
- No dependency on other asset modules (this is the foundation)

## Related Screens

- All Asset List screens (base table support)
- All Asset Detail drawers (attachments tab)
- Asset Form (code generation, location cascader)

## Related APIs

- Base asset CRUD (inherited by type modules)
- `POST /api/asset/attachment/upload` - File upload
- `GET /api/asset/location/tree` - Location hierarchy
- Status change APIs (used internally)

## Test Requirements

- Unit tests: AssetCodeGeneratorTest, AssetStatusServiceTest
- Integration tests: Code generation concurrency, status transitions
- Performance: Code generation < 500ms under 50 concurrent

## Acceptance Criteria

1. All fine-grained implementation tasks are created in ai/tasks/core/
2. Each task has specific, testable acceptance criteria
3. Task dependencies are correctly defined
4. Base entity and database schema tasks are prioritized
5. Code generator and status machine tasks are included
6. Attachment and location hierarchy tasks are included
7. Test requirements from QA strategy are addressed
