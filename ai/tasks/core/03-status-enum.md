---
id: core.status-enum
module: core
priority: 103
status: failing
version: 1
origin: spec-workflow
dependsOn: []
tags:
  - backend
  - enum
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
    cases:
      - "should have 9 status values"
      - "should validate status transitions"
---
# Create Asset Status Enum and Transition Validation

## Context

9-state status machine with transition validation. States cannot transition arbitrarily - certain transitions are invalid (e.g., SCRAPPED cannot change back to AVAILABLE).

## Acceptance Criteria

1. Create `AssetStatusEnum` in `com.ruoyi.asset.domain.enums`:
   - UNDER_CONSTRUCTION("under_construction", "在建/未投入")
   - AVAILABLE_SELF("available_self", "可用-自用")
   - AVAILABLE_RENT("available_rent", "可用-对外出租")
   - AVAILABLE_IDLE("available_idle", "可用-空置")
   - TEMP_CLOSED("temp_closed", "暂停使用")
   - FAULT("fault", "故障/停用")
   - MAINTAINING("maintaining", "维修中")
   - TO_BE_SCRAPPED("to_be_scrapped", "计划报废")
   - SCRAPPED("scrapped", "已报废")

2. Create transition validation map in enum:
   - Define valid transitions for each status
   - SCRAPPED is terminal (no valid transitions out)
   - UNDER_CONSTRUCTION can only go to AVAILABLE_* states
   - MAINTAINING can go back to original status

3. Add method `canTransitionTo(AssetStatusEnum target)`:
   - Returns true if transition is valid
   - Returns false for invalid transitions

4. Add method `getValidTransitions()`:
   - Returns Set<AssetStatusEnum> of valid target states

5. Unit test `AssetStatusEnumTransitionTest`:
   - Test all 9 status values exist
   - Test valid transitions return true
   - Test invalid transitions (e.g., SCRAPPED -> AVAILABLE_SELF) return false
   - Test terminal state has no valid transitions

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (9-State Status Machine)
- UX: ai/tasks/spec/UX-DESIGN.md (Status badge colors)
