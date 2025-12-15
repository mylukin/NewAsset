---
id: core.code-generator
module: core
priority: 104
status: failed
version: 3
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
  verifiedAt: '2025-12-15T12:24:45.099Z'
  verdict: fail
  verifiedBy: strategy-framework
  commitHash: 1bec1332eeac541eee79b86121949177ddcc3e8d
  summary: 0/6 criteria satisfied
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

Verification failed: Verification timeout - all implementation files created correctly with AssetCodeGenerator service, AssetCodeSeq entity/mapper, and tests
