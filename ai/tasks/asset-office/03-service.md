---
id: asset-office.service
module: asset-office
priority: 603
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-office.entity
  - core.base-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/asset-office/**/*.test.*
---
# Implement Office Asset Service

## Context

Service layer for office asset CRUD operations. Extends base asset service with office-specific fields.

## Acceptance Criteria

1. Create `IAssetOfficeService` interface:
2. Create `AssetOfficeServiceImpl`:
3. Implement query with filters:
4. All service methods have proper error handling
## Technical Notes

- Reference: ai/tasks/asset-house/03-service.md (similar structure)
- Use @Transactional for multi-table operations
