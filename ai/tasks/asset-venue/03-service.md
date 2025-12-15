---
id: asset-venue.service
module: asset-venue
priority: 503
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - asset-venue.entity
  - core.base-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/asset-venue/**/*.test.*
---
# Implement Venue Asset Service

## Context

Service layer for venue asset CRUD operations. Extends base asset service with venue-specific fields.

## Acceptance Criteria

1. Create `IAssetVenueService` interface:
2. Create `AssetVenueServiceImpl`:
3. Implement query with filters:
4. All service methods have proper error handling
## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
