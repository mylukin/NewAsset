---
id: core.code-generator
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.database-schema, core.asset-base-entity]
supersedes: []
tags: [backend, service]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Asset Code Generator

## Context

The asset code generator creates unique, configurable asset codes based on rules defined in `t_asset_code_rule`. This is a critical component for asset identification.

## Acceptance Criteria

1. Create `AssetCodeRule` entity mapping `t_asset_code_rule`
2. Create `AssetCodeSeq` entity mapping `t_asset_code_seq`
3. Create `AssetCodeRuleMapper` and `AssetCodeSeqMapper`
4. Create `LocationInfo` value object for location parameters (building, floor, etc.)
5. Implement `AssetCodeRuleService`:
   - `getRuleByAssetType(AssetTypeEnum type)` - get active rule for asset type
   - Cache rules using Spring Cache
6. Implement `AssetCodeSequenceRepository`:
   - `getNextSequence(Long ruleId, Long projectId, Integer year)` - with optimistic lock
7. Implement `AssetCodeGenerator`:
   - `generate(AssetTypeEnum type, Long projectId, LocationInfo location)` - main generation method
   - Parse pattern template and replace placeholders ({PROJECT}, {TYPE}, {BUILDING}, {FLOOR}, {SEQ})
   - Handle sequence number padding (e.g., 4-digit with leading zeros)
8. Implement retry logic for concurrent generation conflicts (max 3 retries)
9. Throw `ServiceException` when no valid rule exists

## Technical Notes

- Reference: TECH.md section 5.1
- Pattern: Optimistic locking with version field
- Use `@Transactional` to ensure atomicity
