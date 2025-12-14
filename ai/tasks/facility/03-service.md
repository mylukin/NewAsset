---
id: facility.service
module: facility
priority: 22
status: failing
version: 16
origin: manual
dependsOn:
  - facility.mapper
  - core.code-generator
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Service

## Context

Service layer for facility asset business logic with code generation integration.

## Acceptance Criteria

1. Create `IAssetFacilityService` interface
2. Create `AssetFacilityServiceImpl` implementation
3. Implement standard CRUD methods:
   - selectAssetFacilityList, selectAssetFacilityById
   - insertAssetFacility (with code generation)
   - updateAssetFacility, deleteAssetFacilityByIds
4. Integrate AssetCodeGenerator for code generation
5. Add @DataScope for project filtering
6. Use @Transactional for write operations

## Technical Notes

- Reference: PRD Section 6.3
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetFacilityServiceImpl`
