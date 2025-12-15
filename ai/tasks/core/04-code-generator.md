---
id: core.code-generator
module: core
priority: 104
status: passing
version: 8
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
  generatedAt: '2025-12-15T15:13:58.120Z'
  generatedBy: claude
  forVersion: 4
  suggestedTestFiles:
    unit:
      - tests/core/code-generator.test.ts
    e2e: []
  unitTestCases:
    - name: should create AssetCodeGenerator service in correct package
      assertions:
        - expect(AssetCodeGenerator).toBeDefined()
        - expect(typeof AssetCodeGenerator.generateCode).toBe('function')
    - name: should generate code with TYPE_PREFIX-6_DIGIT_SEQ format
      assertions:
        - const code = await AssetCodeGenerator.generateCode('ASSET');
        - 'expect(code).toMatch(/^[A-Z]+-\d{6}$/);'
        - expect(code.startsWith('ASSET-')).toBe(true)
    - name: should handle concurrent code generation with optimistic locking
      assertions:
        - >-
          const promises = Array(10).fill(null).map(() =>
          AssetCodeGenerator.generateCode('TEST'));
        - const results = await Promise.all(promises);
        - expect(results).toHaveLength(10);
        - expect(new Set(results).size).toBe(10)
    - name: should initialize sequence on first use
      assertions:
        - await AssetCodeGenerator.resetSequence('NEW_TYPE');
        - const code = await AssetCodeGenerator.generateCode('NEW_TYPE');
        - expect(code).toMatch(/^NEW_TYPE-000001$/);
    - name: should add unique constraint check for generated codes
      assertions:
        - await AssetCodeGenerator.generateCode('CONSTRAINT');
        - >-
          expect(mockRepository.save).toHaveBeenCalledWith(expect.objectContaining({
        - '  code: expect.stringMatching(/^CONSTRAINT-\d{6}$/)'
        - '}));'
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
