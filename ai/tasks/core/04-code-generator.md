---
id: core.code-generator
module: core
priority: 104
status: passing
version: 10
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
    cases:
      - should generate sequential code
      - should handle concurrent generation
      - should format code correctly
verification:
  verifiedAt: '2025-12-15T15:20:00.995Z'
  verdict: fail
  verifiedBy: strategy-framework
  commitHash: 02b90ed62f67c329ce68bef1a848df4c9436b074
  summary: 0/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T15:23:11.322Z'
  generatedBy: claude
  forVersion: 8
  suggestedTestFiles:
    unit:
      - tests/core/code-generator.test.ts
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetCodeGenerator service in com.ruoyi.asset.service.rule
        package
      assertions:
        - expect(AssetCodeGenerator).toBeDefined()
        - expect(AssetCodeGenerator.name).toBe('AssetCodeGenerator')
    - name: should generate code with TYPE_PREFIX-6_DIGIT_SEQ format
      assertions:
        - 'expect(generateCode(''FIXED'')).toMatch(/^[A-Z]+-\d{6}$/)'
        - 'expect(generateCode(''ASSET'')).toMatch(/^ASSET-\d{6}$/)'
        - expect(generateCode('EQ').length).toBeGreaterThan(0)
    - name: should implement optimistic locking for concurrent generation
      assertions:
        - expect(gen1.getVersion()).toBeDefined()
        - >-
          expect(() => gen1.updateWithConflict(existingVersion,
          newCode)).not.toThrow()
        - 'expect(() => gen1.updateWithConflict(staleVersion, newCode)).toThrow()'
    - name: should handle sequence initialization
      assertions:
        - expect(initSequence('FIXED')).toBe(true)
        - expect(getCurrentSequence('FIXED')).toBe(1)
        - expect(initSequence('FIXED')).toBe(false)
    - name: should enforce unique constraint for generated codes
      assertions:
        - expect(() => saveCode('FIXED-000001')).not.toThrow()
        - expect(() => saveCode('FIXED-000001')).toThrow()
        - expect(saveCode('FIXED-000002')).toBe(true)
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement Asset Code Generator Service

## Context

Auto-generate unique asset codes in format HA-000001 (type prefix + 6-digit sequence). Uses global sequence with optimistic locking for concurrency control.

## Acceptance Criteria

1. Create `AssetCodeGenerator` service in `com.ruoyi.asset.service.rule`:
2. Code format: `{TYPE_PREFIX}-{6_DIGIT_SEQ}`:
3. Implement optimistic locking for concurrent generation:
4. Handle sequence initialization:
5. Unit test `AssetCodeGeneratorTest`:
6. Add unique constraint check:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Global sequential codes)
- Performance target: < 500ms under 50 concurrent requests
- QA: ai/tasks/spec/QA-STRATEGY.md (Load test concurrent creates)

## Notes

Verification failed: Verification failed
