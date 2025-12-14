---
id: core.code-generator
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn:
  - core.database-schema
  - core.asset-base-entity
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Implement Asset Code Generator

## Acceptance Criteria

1. Create `AssetCodeRule` entity mapping `t_asset_code_rule`
2. Create `AssetCodeSeq` entity mapping `t_asset_code_seq`
3. Create `AssetCodeRuleMapper` and `AssetCodeSeqMapper`
4. Create `LocationInfo` value object for location parameters (building, floor, etc.)
5. Implement `AssetCodeRuleService`:
6. Implement `AssetCodeSequenceRepository`:
7. Implement `AssetCodeGenerator`:
8. Implement retry logic for concurrent generation conflicts (max 3 retries)
9. Throw `ServiceException` when no valid rule exists
