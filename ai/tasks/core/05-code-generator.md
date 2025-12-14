---
id: core.code-generator
module: core
priority: 5
status: passing
version: 10
origin: manual
dependsOn:
  - core.database-schema
  - core.asset-status-enum
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:01:03.417Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: c13ac876720ec39f1860be7a656d2a0ea280d0e1
  summary: 9/9 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:59:33.880Z'
  generatedBy: claude
  forVersion: 8
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetCodeRuleServiceTest.java
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/repository/AssetCodeSequenceRepositoryTest.java
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetCodeGeneratorTest.java
    e2e: []
  unitTestCases:
    - name: >-
        AssetCodeRuleService should load and manage code rules from
        t_asset_code_rule table
      assertions:
        - assertNotNull(ruleService.findByAssetType(AssetType.EQUIPMENT))
        - 'assertEquals(expectedPattern, rule.getPattern())'
    - name: >-
        AssetCodeSequenceRepository should persist and retrieve sequences from
        t_asset_code_seq
      assertions:
        - 'assertNotNull(repository.findByProjectIdAndYear(projectId, year))'
        - 'assertEquals(expectedSeq, sequence.getCurrentValue())'
    - name: >-
        AssetCodeGenerator.generate should produce code for given AssetType,
        projectId, and LocationInfo
      assertions:
        - >-
          assertNotNull(generator.generate(AssetType.EQUIPMENT, projectId,
          locationInfo))
        - assertTrue(generatedCode.matches(expectedPattern))
    - name: >-
        AssetCodeGenerator should replace pattern placeholders PROJECT, TYPE,
        BUILDING, FLOOR, SEQ correctly
      assertions:
        - >-
          assertEquals("PRJ001-EQ-A-03-0001", generator.generate(type,
          projectId, location))
        - 'assertFalse(generatedCode.contains("{PROJECT}"))'
        - 'assertFalse(generatedCode.contains("{SEQ}"))'
    - name: >-
        AssetCodeSequenceRepository should use optimistic locking for concurrent
        sequence generation
      assertions:
        - >-
          assertThrows(OptimisticLockingFailureException.class, () ->
          concurrentUpdate())
        - 'assertEquals(expectedVersion + 1, sequence.getVersion())'
    - name: AssetCodeGenerator should reset sequence based on project and year scope
      assertions:
        - 'assertEquals(1, generator.getNextSequence(projectId, newYear))'
        - 'assertNotEquals(oldSequence, newYearSequence)'
    - name: >-
        AssetCodeGenerator should retry on unique constraint violation up to max
        N retries
      assertions:
        - >-
          assertDoesNotThrow(() -> generator.generateWithRetry(type, projectId,
          location))
        - 'verify(repository, times(expectedRetries)).save(any())'
    - name: >-
        AssetCodeGenerator should provide code preview for admin validation
        without incrementing sequence
      assertions:
        - >-
          assertEquals(previewCode, generator.preview(type, projectId,
          location))
        - 'assertEquals(originalSeq, repository.getCurrentSequence(projectId))'
    - name: AssetCodeRuleService should cache rules for performance
      assertions:
        - 'verify(ruleRepository, times(1)).findAll()'
        - 'assertSame(cachedRule, ruleService.findByAssetType(type))'
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Implement Asset Code Generator Service

## Context

Each asset requires a unique code generated according to configurable rules. The generator must handle concurrent requests safely and support different patterns per asset type.

## Acceptance Criteria

1. Create `AssetCodeRuleService` to manage code rules from `t_asset_code_rule`
2. Create `AssetCodeSequenceRepository` for sequence management with `t_asset_code_seq`
3. Create `AssetCodeGenerator` with method `generate(AssetType type, Long projectId, LocationInfo locationInfo)`
4. Support pattern placeholders: `{PROJECT}`, `{TYPE}`, `{BUILDING}`, `{FLOOR}`, `{SEQ}`
5. Implement optimistic locking for concurrent sequence generation
6. Handle sequence reset by project/year scope
7. Implement retry logic on unique constraint violation (max N retries)
8. Add code preview functionality for admin validation
9. Cache rules for performance
## Technical Notes

- Reference: TECH.md Section 5.1
- Pattern: Template pattern for code generation
- Concurrency: Optimistic lock with version field or SELECT FOR UPDATE
- Location: `com.ruoyi.asset.service.rule.AssetCodeGenerator`
