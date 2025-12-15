---
id: asset-venue.service
module: asset-venue
priority: 503
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.entity
  - core.base-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-venue/**/*.test.*"
---
# Implement Venue Asset Service

## Context

Service layer for venue asset CRUD operations. Extends base asset service with venue-specific fields.

## Acceptance Criteria

1. Create `IAssetVenueService` interface:
   - list(AssetVenueQueryDTO query) - Paginated list with filters
   - getById(Long id) - Get venue detail with base asset
   - create(AssetVenueCreateDTO dto) - Create venue asset
   - update(AssetVenueUpdateDTO dto) - Update venue asset
   - delete(List<Long> ids) - Soft delete with base asset
   - export(AssetVenueQueryDTO query) - Export data

2. Create `AssetVenueServiceImpl`:
   - Transactional create: insert t_asset + t_asset_venue
   - Transactional update: update both tables
   - Transactional delete: soft delete both tables
   - Block delete if open work orders exist (per OVERVIEW.md decision)

3. Implement query with filters:
   - Project, building, floor (from base asset)
   - Venue type, usage mode, availability status (from extension)

4. All service methods have proper error handling

## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
