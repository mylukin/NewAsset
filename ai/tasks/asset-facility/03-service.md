---
id: asset-facility.service
module: asset-facility
priority: 403
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.entity
  - core.base-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-facility/**/*.test.*"
---
# Implement Facility Asset Service

## Context

Service layer for facility asset CRUD operations. Extends base asset service with facility-specific fields.

## Acceptance Criteria

1. Create `IAssetFacilityService` interface:
   - list(AssetFacilityQueryDTO query) - Paginated list with filters
   - getById(Long id) - Get facility detail with base asset
   - create(AssetFacilityCreateDTO dto) - Create facility asset
   - update(AssetFacilityUpdateDTO dto) - Update facility asset
   - delete(List<Long> ids) - Soft delete with base asset
   - export(AssetFacilityQueryDTO query) - Export data

2. Create `AssetFacilityServiceImpl`:
   - Transactional create: insert t_asset + t_asset_facility
   - Transactional update: update both tables
   - Transactional delete: soft delete both tables
   - Block delete if open work orders exist (per OVERVIEW.md decision)

3. Implement query with filters:
   - Project, building, floor (from base asset)
   - Facility type, status (from extension)
   - Warranty expiring soon filter

4. All service methods have proper error handling

## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
