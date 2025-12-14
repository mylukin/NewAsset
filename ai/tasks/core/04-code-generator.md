---
id: core.code-generator
module: core
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [domain, business-logic, critical]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Asset Code Generation System

## Context

The asset coding system generates unique, rule-based codes for each asset. This is a critical component that ensures global uniqueness and follows configurable patterns per asset type.

## Acceptance Criteria

1. `AssetCodeRule` entity created for rule definitions:
   - assetType, ruleName, pattern, separator
   - seqLength, resetScope (NONE/BY_PROJECT/BY_PROJECT_YEAR)
   - enabled flag
2. `AssetCodeSeq` entity created for sequence tracking:
   - ruleId, projectId (nullable), year (nullable)
   - currentSeq, version (optimistic lock)
3. `AssetCodeRuleService` implemented:
   - Load and cache rules by asset type
   - CRUD operations for rule management
4. `AssetCodeGenerator` implemented:
   - `generate(AssetType, projectId, LocationInfo)` method
   - Parse pattern placeholders: {PROJECT}, {TYPE}, {BUILDING}, {FLOOR}, {SEQ}
   - Sequence number padding (e.g., 4 digits with leading zeros)
5. Concurrency handling:
   - Optimistic locking with retry mechanism (max 3 retries)
   - Unique constraint on `t_asset.asset_code`
6. Unit tests cover:
   - Pattern parsing and code generation
   - Sequence increment logic
   - Concurrent access simulation
   - Different reset scope behaviors

## Technical Notes

- Pattern example: `{PROJECT}-{TYPE}-{BUILDING}-{FLOOR}-{SEQ}`
- Transaction must wrap sequence update + asset insert
- Consider using Redis for high-concurrency scenarios (future optimization)
