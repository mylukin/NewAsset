---
id: house.service
module: house
priority: 12
status: failing
version: 14
origin: manual
dependsOn:
  - house.mapper
  - core.code-generator
  - core.state-machine
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Service

## Context

Service layer for house asset business logic, coordinating code generation, status management, and CRUD operations.

## Acceptance Criteria

1. Create `IAssetHouseService` interface
2. Create `AssetHouseServiceImpl` implementation
3. Implement methods:
   - `selectAssetHouseList(AssetHouse query)` - list with pagination
   - `selectAssetHouseById(Long id)` - get detail
   - `insertAssetHouse(AssetHouse house)` - create with auto-generated code
   - `updateAssetHouse(AssetHouse house)` - update with change logging
   - `deleteAssetHouseByIds(Long[] ids)` - soft delete
   - `copyAssetHouse(Long sourceId)` - copy for same-floor scenarios
4. Integrate `AssetCodeGenerator` for code generation on insert
5. Integrate `AssetStatusService` for status changes
6. Add @DataScope annotation for project-based filtering
7. Use @Transactional for insert/update operations

## Technical Notes

- Reference: PRD Section 6.2.2 (copy feature)
- Pattern: Service + Mapper pattern with domain service integration
- Location: `com.ruoyi.asset.service.impl.AssetHouseServiceImpl`
