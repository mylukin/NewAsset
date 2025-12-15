---
id: asset-house.entity
module: asset-house
priority: 202
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - asset-house.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-house/**/*.test.*
verification:
  verifiedAt: '2025-12-15T13:17:38.810Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 23156e39a4a994a0c0424979f863efbb45a8a297
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:23:32.220Z'
  generatedBy: claude
  forVersion: 2
  suggestedTestFiles:
    unit:
      - tests/asset-house/entity.test.ts
    e2e:
      - e2e/asset-house/entity.spec.ts
  unitTestCases:
    - name: should create AssetHouse entity with required properties
      assertions:
        - expect(AssetHouse).toBeDefined()
        - expect(new AssetHouse()).toHaveProperty('id')
        - expect(new AssetHouse()).toHaveProperty('houseName')
        - expect(new AssetHouse()).toHaveProperty('address')
    - name: should create AssetHouseMapper interface with required methods
      assertions:
        - expect(AssetHouseMapper).toBeDefined()
        - expect(typeof AssetHouseMapper.selectByPrimaryKey).toBe('function')
        - expect(typeof AssetHouseMapper.insert).toBe('function')
        - expect(typeof AssetHouseMapper.updateByPrimaryKey).toBe('function')
    - name: should create DTO and VO classes for data transfer
      assertions:
        - expect(AssetHouseDTO).toBeDefined()
        - expect(AssetHouseVO).toBeDefined()
        - expect(new AssetHouseDTO()).toHaveProperty('houseName')
        - expect(new AssetHouseVO()).toHaveProperty('displayName')
    - name: should compile without errors
      assertions:
        - >-
          expect(() =>
          require/com.ruoyi.asset.domain.entity.AssetHouse).not.toThrow()
        - >-
          expect(() =>
          require/com.ruoyi.asset.mapper.AssetHouseMapper).not.toThrow()
  e2eScenarios:
    - name: asset house entity can be accessed via API
      steps:
        - navigate to /api/asset/house
        - verify response contains asset house data
        - verify data structure matches entity definition
  frameworkHint: vitest
---
# Create House Asset Entity and Mapper

## Context

Java entity class for house extension table. Linked to base Asset entity via assetId.

## Acceptance Criteria

1. Create `AssetHouse` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetHouseMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Database Design)
- Extension entity joins with base Asset entity
