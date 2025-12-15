---
id: core.base-service
module: core
priority: 108
status: failed
version: 2
origin: spec-workflow
dependsOn:
  - core.asset-entity
  - core.code-generator
  - core.status-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Implement Base Asset Service

## Context

Base service with common asset CRUD operations. Extended by type-specific services (house, parking, etc.).

## Acceptance Criteria

1. Create `IAssetService` interface in `com.ruoyi.asset.service`:
2. Create `AssetServiceImpl` implementing IAssetService:
3. Create operation:
4. Delete operation:
5. List operation with filters:
6. Data permission:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Delete rule: block if open WO)
- Extended by house, parking, facility, venue, office services

## Notes

Verification failed: Implementation complete - IAssetService interface, AssetServiceImpl with CRUD, DTOs, and pagination
