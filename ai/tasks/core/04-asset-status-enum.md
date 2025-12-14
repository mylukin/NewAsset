---
id: core.asset-status-enum
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [backend, enum]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Create Asset Status Enum

## Context

The unified asset status machine defines all possible states an asset can be in. This enum is critical for state management and workflow integration.

## Acceptance Criteria

1. Create `AssetStatusEnum` in `com.ruoyi.asset.domain.enums` with values:
   - `UNDER_CONSTRUCTION` - 在建/未投入使用
   - `AVAILABLE_SELF` - 可用-自用
   - `AVAILABLE_RENT` - 可用-对外出租
   - `AVAILABLE_IDLE` - 可用-空置
   - `TEMP_CLOSED` - 暂停使用
   - `FAULT` - 故障/停用
   - `MAINTAINING` - 维修中
   - `TO_BE_SCRAPPED` - 计划报废
   - `SCRAPPED` - 已报废

2. Include code and description fields for each enum value
3. Provide utility methods:
   - `getByCode(String code)`
   - `getDescription()`
   - `isAvailable()` - returns true for AVAILABLE_* states
   - `canTransitionTo(AssetStatusEnum target)` - validates state transitions

## Technical Notes

- Reference: PRD section 4.4, TECH.md section 5.2.1
- Pattern: Java enum with static lookup methods
