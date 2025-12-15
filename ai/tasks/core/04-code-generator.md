---
id: core.code-generator
module: core
priority: 104
status: failing
version: 1
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
tddGuidance:
  generatedAt: '2025-12-15T12:29:00.526Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/code-generator.test.ts
    e2e: []
  unitTestCases:
    - name: should create AssetCodeGenerator service with required methods
      assertions:
        - expect(AssetCodeGenerator).toBeDefined()
        - expect(typeof assetCodeGenerator.generateCode).toBe('function')
        - expect(typeof assetCodeGenerator.initializeSequence).toBe('function')
    - name: should generate code in TYPE_PREFIX-6_DIGIT_SEQ format
      assertions:
        - 'expect(generatedCode).toMatch(/^[A-Z]+-\d{6}$/)'
        - expect(codeParts.length).toBe(2)
        - 'expect(codeParts[0]).toBe(''ASSET'')'
        - 'expect(codeParts[1]).toHaveLength(6)'
    - name: should handle concurrent code generation with optimistic locking
      assertions:
        - expect(concurrentResults.length).toBe(10)
        - expect(new Set(concurrentResults).size).toBe(10)
        - expect(noDuplicates).toBe(true)
    - name: should initialize sequence for asset types
      assertions:
        - expect(initializationResult).toBe(true)
        - expect(sequenceValue).toBe(1)
        - expect(assetCodeGenerator.getNextSequence('ASSET')).toBe(1)
    - name: should ensure generated codes are unique
      assertions:
        - expect(generatedCodes.length).toBe(100)
        - expect(new Set(generatedCodes).size).toBe(100)
        - expect(duplicateCheckResult).toBe(false)
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
