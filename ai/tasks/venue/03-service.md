---
id: venue.service
module: venue
priority: 41
status: failing
version: 20
origin: manual
dependsOn:
  - venue.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Service

## Context

Service layer for venue asset business logic.

## Acceptance Criteria

1. Create `IAssetVenueService` interface
2. Create `AssetVenueServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations

## Technical Notes

- Reference: PRD Section 6.5
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetVenueServiceImpl`
