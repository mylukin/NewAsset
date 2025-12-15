---
id: core.asset-entity
module: core
priority: 102
status: passing
version: 3
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
  verifiedAt: '2025-12-15T12:05:25.103Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7378be2ea7d8558992e919b4362b6be28771672b
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T11:43:46.397Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/asset-entity.test.ts
    e2e: []
  unitTestCases:
    - name: should create Asset entity class with required fields
      assertions:
        - expect(Asset.class).toBeDefined()
        - expect(Asset.class.getDeclaredFields().length).toBeGreaterThan(0)
    - name: should create AssetMapper interface
      assertions:
        - expect(AssetMapper.class).toBeDefined()
        - >-
          expect(AssetMapper.class.getDeclaredMethods().length).toBeGreaterThan(0)
    - name: should create valid mapper XML file
      assertions:
        - expect(mapperXmlFile.exists()).toBe(true)
        - expect(mapperXmlContent).toContain('Asset')
    - name: should create AssetVO and AssetDTO classes
      assertions:
        - expect(AssetVO.class).toBeDefined()
        - expect(AssetDTO.class).toBeDefined()
    - name: should compile without errors and have valid XML
      assertions:
        - expect(compileResult.success).toBe(true)
        - expect(xmlValidationResult.valid).toBe(true)
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
