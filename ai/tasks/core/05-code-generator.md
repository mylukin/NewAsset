---
id: core.code-generator
module: core
priority: 5
status: failing
version: 3
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
