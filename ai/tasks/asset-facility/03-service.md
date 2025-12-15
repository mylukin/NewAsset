---
id: asset-facility.service
module: asset-facility
priority: 403
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.entity
  - core.base-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/asset-facility/**/*.test.*
---
# Implement Facility Asset Service

## Context

Service layer for facility asset CRUD operations. Extends base asset service with facility-specific fields.

## Acceptance Criteria

1. Create `IAssetFacilityService` interface:
2. Create `AssetFacilityServiceImpl`:
3. Implement query with filters:
4. All service methods have proper error handling
## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
