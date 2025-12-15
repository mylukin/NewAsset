---
id: core.code-generator
module: core
priority: 104
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
    cases:
      - "should generate sequential code"
      - "should handle concurrent generation"
      - "should format code correctly"
---
# Implement Asset Code Generator Service

## Context

Auto-generate unique asset codes in format HA-000001 (type prefix + 6-digit sequence). Uses global sequence with optimistic locking for concurrency control.

## Acceptance Criteria

1. Create `AssetCodeGenerator` service in `com.ruoyi.asset.service.rule`:
   - Method: `generate(String assetType)` returns formatted code
   - Uses t_asset_code_seq table for sequence management

2. Code format: `{TYPE_PREFIX}-{6_DIGIT_SEQ}`:
   - HOUSE → HA-000001
   - PARKING → PA-000001
   - FACILITY → FA-000001
   - VENUE → VE-000001
   - OFFICE → OF-000001

3. Implement optimistic locking for concurrent generation:
   - Read current_seq and version from t_asset_code_seq
   - Increment seq, update with version check
   - If update fails (version mismatch), retry up to 3 times
   - Throw ServiceException if all retries fail

4. Handle sequence initialization:
   - If no record exists for asset_type, create with seq=0
   - First generated code will be {TYPE}-000001

5. Unit test `AssetCodeGeneratorTest`:
   - Test correct format for each asset type
   - Test sequential increment
   - Mock concurrent generation scenario
   - Test retry on version conflict

6. Add unique constraint check:
   - If generated code already exists in t_asset (unlikely), regenerate

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Global sequential codes)
- Performance target: < 500ms under 50 concurrent requests
- QA: ai/tasks/spec/QA-STRATEGY.md (Load test concurrent creates)
