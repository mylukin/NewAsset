---
id: asset-office.service
module: asset-office
priority: 603
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.entity
  - core.base-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-office/**/*.test.*"
---
# Implement Office Asset Service

## Context

Service layer for office asset CRUD operations. Extends base asset service with office-specific fields.

## Acceptance Criteria

1. Create `IAssetOfficeService` interface:
   - list(AssetOfficeQueryDTO query) - Paginated list with filters
   - getById(Long id) - Get office asset detail with base asset
   - create(AssetOfficeCreateDTO dto) - Create office asset
   - update(AssetOfficeUpdateDTO dto) - Update office asset
   - delete(List<Long> ids) - Soft delete with base asset
   - export(AssetOfficeQueryDTO query) - Export data

2. Create `AssetOfficeServiceImpl`:
   - Transactional create: insert t_asset + t_asset_office
   - Transactional update: update both tables
   - Transactional delete: soft delete both tables
   - Block delete if open work orders exist (per OVERVIEW.md decision)

3. Implement query with filters:
   - Project (from base asset)
   - Office type, use department, user name (from extension)
   - Responsible person, status (from base asset)

4. All service methods have proper error handling

## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
