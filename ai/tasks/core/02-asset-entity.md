---
id: core.asset-entity
module: core
priority: 102
status: failing
version: 5
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
  generatedAt: '2025-12-15T15:07:38.945Z'
  generatedBy: claude
  forVersion: 3
  suggestedTestFiles:
    unit:
      - tests/core/asset-entity.test.ts
    e2e: []
  unitTestCases:
    - name: should have Asset entity class with required fields and annotations
      assertions:
        - expect(assetEntityFile).toContain('@TableName')
        - expect(assetEntityFile).toContain('private Long id')
        - >-
          expect(assetEntityFile).toContain('package
          com.ruoyi.asset.domain.entity')
    - name: should have AssetMapper interface extending BaseMapper
      assertions:
        - expect(assetMapperFile).toContain('interface AssetMapper')
        - expect(assetMapperFile).toContain('@Mapper')
        - expect(assetMapperFile).toContain('package com.ruoyi.asset.mapper')
    - name: should have valid mapper XML with proper namespace
      assertions:
        - >-
          expect(mapperXmlFile).toContain('namespace="com.ruoyi.asset.mapper.AssetMapper"')
        - expect(mapperXmlFile).toContain('<resultMap')
    - name: should have AssetVO and AssetDTO classes in domain.vo package
      assertions:
        - expect(assetVoFile).toContain('class AssetVO')
        - expect(assetDtoFile).toContain('class AssetDTO')
        - expect(assetVoFile).toContain('package com.ruoyi.asset.domain.vo')
    - name: should compile all entity and mapper classes without errors
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
