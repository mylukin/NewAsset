---
id: office.service
module: office
priority: 50
status: failing
version: 16
origin: manual
dependsOn:
  - office.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Service

## Context

Service layer for office asset business logic.

## Acceptance Criteria

1. Create `IAssetOfficeService` interface
2. Create `AssetOfficeServiceImpl` implementation
3. Implement standard CRUD methods with code generation
4. Add @DataScope for project filtering
5. Use @Transactional for write operations

## Technical Notes

- Reference: PRD Section 6.6
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetOfficeServiceImpl`
