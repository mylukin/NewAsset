---
id: core.asset-entity
module: core
priority: 102
status: passing
version: 8
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
  generatedAt: '2025-12-15T23:26:27.150Z'
  generatedBy: codex
  forVersion: 7
  suggestedTestFiles:
    unit:
      - tests/core/asset-entity.test.ts
    e2e:
      - e2e/core/asset-entity.spec.ts
  unitTestCases:
    - name: should create Asset entity class at expected package path
      assertions:
        - >-
          expect(existsSync('core/src/main/java/com/ruoyi/asset/domain/entity/Asset.java')).toBe(true)
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/domain/entity/Asset.java','utf-8')).toContain('package
          com.ruoyi.asset.domain.entity;')
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/domain/entity/Asset.java','utf-8')).toContain('class
          Asset')
    - name: should create AssetMapper interface at expected package path
      assertions:
        - >-
          expect(existsSync('core/src/main/java/com/ruoyi/asset/mapper/AssetMapper.java')).toBe(true)
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/mapper/AssetMapper.java','utf-8')).toContain('package
          com.ruoyi.asset.mapper;')
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/mapper/AssetMapper.java','utf-8')).toContain('interface
          AssetMapper')
    - name: should provide valid MyBatis mapper XML when not using MyBatis-Plus
      assertions:
        - >-
          expect(existsSync('core/src/main/resources/mapper/asset/AssetMapper.xml')).toBe(true)
        - >-
          expect(readFileSync('core/src/main/resources/mapper/asset/AssetMapper.xml','utf-8')).toMatch(/<mapper[^>]*namespace="com\.ruoyi\.asset\.mapper\.AssetMapper"/)
        - >-
          expect(readFileSync('core/src/main/resources/mapper/asset/AssetMapper.xml','utf-8')).toContain('</mapper>')
    - name: should create AssetVO and AssetDTO classes under domain vo package
      assertions:
        - >-
          expect(existsSync('core/src/main/java/com/ruoyi/asset/domain/vo/AssetVO.java')).toBe(true)
        - >-
          expect(existsSync('core/src/main/java/com/ruoyi/asset/domain/vo/AssetDTO.java')).toBe(true)
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/domain/vo/AssetVO.java','utf-8')).toContain('package
          com.ruoyi.asset.domain.vo;')
        - >-
          expect(readFileSync('core/src/main/java/com/ruoyi/asset/domain/vo/AssetDTO.java','utf-8')).toContain('package
          com.ruoyi.asset.domain.vo;')
    - name: >-
        should compile core module successfully and ensure mapper XML is
        well-formed
      assertions:
        - >-
          expect(execSync('mvn -q -pl core -am
          test-compile',{stdio:'pipe'}).toString()).not.toContain('COMPILATION
          ERROR')
        - >-
          expect(() => new
          (require('xmldom').DOMParser)().parseFromString(readFileSync('core/src/main/resources/mapper/asset/AssetMapper.xml','utf-8'),'text/xml')).not.toThrow()
        - >-
          expect(new
          (require('xmldom').DOMParser)().parseFromString(readFileSync('core/src/main/resources/mapper/asset/AssetMapper.xml','utf-8'),'text/xml').getElementsByTagName('parsererror').length).toBe(0)
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
