---
id: core.asset-entity
module: core
priority: 102
status: passing
version: 6
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-15T15:07:53.338Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ac57aedffb2da0cbe7b08a8b74d3afe05e68fe77
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T22:55:07.534Z'
  generatedBy: claude
  forVersion: 5
  suggestedTestFiles:
    unit:
      - tests/core/asset-entity.test.ts
    e2e: []
  unitTestCases:
    - name: should create Asset entity class with all required fields
      assertions:
        - expect(Asset).toBeDefined()
        - expect(new Asset()).toHaveProperty('id')
        - expect(new Asset()).toHaveProperty('assetCode')
        - expect(new Asset()).toHaveProperty('assetName')
        - expect(new Asset()).toHaveProperty('categoryId')
        - expect(new Asset()).toHaveProperty('status')
    - name: should create AssetMapper interface with CRUD operations
      assertions:
        - expect(AssetMapper).toBeDefined()
        - expect(typeof AssetMapper.selectById).toBe('function')
        - expect(typeof AssetMapper.insert).toBe('function')
        - expect(typeof AssetMapper.updateById).toBe('function')
        - expect(typeof AssetMapper.deleteById).toBe('function')
    - name: should have valid mapper XML configuration
      assertions:
        - expect(mapperXmlExists).toBe(true)
        - >-
          expect(mapperXmlContent).toContain('namespace="com.ruoyi.asset.mapper.AssetMapper"')
        - expect(mapperXmlContent).toContain('resultMap')
    - name: should create AssetVO class with view properties
      assertions:
        - expect(AssetVO).toBeDefined()
        - expect(new AssetVO()).toHaveProperty('id')
        - expect(new AssetVO()).toHaveProperty('assetCode')
        - expect(new AssetVO()).toHaveProperty('categoryName')
    - name: should create AssetDTO class with transfer properties
      assertions:
        - expect(AssetDTO).toBeDefined()
        - expect(new AssetDTO()).toHaveProperty('assetCode')
        - expect(new AssetDTO()).toHaveProperty('assetName')
        - expect(new AssetDTO()).toHaveProperty('categoryId')
    - name: should compile entity and mapper without errors
      assertions:
        - expect(compileResult.exitCode).toBe(0)
        - expect(compileResult.errors).toHaveLength(0)
  e2eScenarios: []
  frameworkHint: vitest
---
# Create Asset Base Entity and Mapper

## Context

Java entity class and MyBatis mapper for the base asset table. Forms the foundation for all asset type modules.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`:
2. Create `AssetMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file (if not using MyBatis-Plus):
4. Create `AssetVO` and `AssetDTO` classes in `com.ruoyi.asset.domain.vo`:
5. Entity compiles without errors and mapper XML is valid
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Tech Stack: MyBatis 3.5.x)
- Follow RuoYi entity conventions
- Use BaseEntity from RuoYi for audit fields if available
