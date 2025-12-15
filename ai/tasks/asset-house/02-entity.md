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
  generatedAt: '2025-12-15T13:13:51.709Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-house/entity.test.ts
    e2e: []
  unitTestCases:
    - name: should create AssetHouse entity with expected fields
      assertions:
        - expect(AssetHouse).toBeDefined()
        - expect(new AssetHouse()).toBeInstanceOf(Object)
    - name: should create AssetHouseMapper interface with required methods
      assertions:
        - expect(AssetHouseMapper).toBeDefined()
        - expect(typeof AssetHouseMapper.selectByPrimaryKey).toBe('function')
    - name: should create mapper XML file with proper SQL statements
      assertions:
        - expect(mapperXmlContent).toContain('<mapper')
        - expect(mapperXmlContent).toContain('select')
        - expect(mapperXmlContent).toContain('resultMap')
    - name: should create DTO and VO classes
      assertions:
        - expect(AssetHouseCreateDTO).toBeDefined()
        - expect(AssetHouseUpdateDTO).toBeDefined()
        - expect(AssetHouseVO).toBeDefined()
    - name: should compile without errors
      assertions:
        - expect(buildResult.success).toBe(true)
        - expect(buildResult.errors).toHaveLength(0)
  e2eScenarios: []
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
