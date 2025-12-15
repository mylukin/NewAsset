---
id: core.base-service
module: core
priority: 108
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.asset-entity
  - core.code-generator
  - core.status-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Implement Base Asset Service

## Context

Base service with common asset CRUD operations. Extended by type-specific services (house, parking, etc.).

## Acceptance Criteria

1. Create `IAssetService` interface in `com.ruoyi.asset.service`:
   - `Asset create(AssetCreateDTO dto)`: Create with auto-generated code
   - `Asset update(AssetUpdateDTO dto)`: Update existing asset
   - `void delete(Long[] ids)`: Soft delete (set del_flag='2')
   - `Asset getById(Long id)`: Get by ID
   - `PageResult<AssetVO> list(AssetQueryDTO query)`: Paginated list

2. Create `AssetServiceImpl` implementing IAssetService:
   - Inject AssetMapper, AssetCodeGenerator, AssetStatusService

3. Create operation:
   - Validate required fields
   - Generate asset code via AssetCodeGenerator
   - Set initial status to AVAILABLE_IDLE (or from DTO)
   - Insert into database
   - Return created asset with ID and code

4. Delete operation:
   - Check for open work orders (block if any)
   - Soft delete: update del_flag='2'
   - Error message if blocked: "Cannot delete asset with open work orders"

5. List operation with filters:
   - Support filters: projectId, assetType, status, building, floor, keyword
   - Support pagination (pageNum, pageSize)
   - Return PageResult with total count

6. Data permission:
   - Apply project-scoped data filter using RuoYi @DataScope
   - Users only see assets in their authorized projects

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Delete rule: block if open WO)
- Extended by house, parking, facility, venue, office services
