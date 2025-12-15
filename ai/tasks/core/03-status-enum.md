---
id: core.status-enum
module: core
priority: 103
status: failing
version: 8
origin: spec-workflow
dependsOn: []
supersedes: []
tags:
  - backend
  - enum
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
    cases:
      - should have 9 status values
      - should validate status transitions
verification:
  verifiedAt: '2025-12-15T15:11:41.595Z'
  verdict: fail
  verifiedBy: strategy-framework
  commitHash: 25e3bd2767b2def5150864cf232816e2f219039a
  summary: 0/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T22:56:50.125Z'
  generatedBy: claude
  forVersion: 6
  suggestedTestFiles:
    unit:
      - tests/core/asset-status-enum-transition.test.ts
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetStatusEnum with 9 status values in
        com.ruoyi.asset.domain.enums
      assertions:
        - expect(Object.keys(AssetStatusEnum)).toHaveLength(9)
        - expect(AssetStatusEnum.UNDER_CONSTRUCTION).toBeDefined()
        - expect(AssetStatusEnum.AVAILABLE_SELF).toBeDefined()
        - expect(AssetStatusEnum.AVAILABLE_RENT).toBeDefined()
        - expect(AssetStatusEnum.AVAILABLE_IDLE).toBeDefined()
        - expect(AssetStatusEnum.TEMP_CLOSED).toBeDefined()
        - expect(AssetStatusEnum.FAULT).toBeDefined()
        - expect(AssetStatusEnum.MAINTAINING).toBeDefined()
        - expect(AssetStatusEnum.TO_BE_SCRAPPED).toBeDefined()
        - expect(AssetStatusEnum.SCRAPPED).toBeDefined()
    - name: should have transition validation map configured for each status
      assertions:
        - >-
          expect(AssetStatusEnum.UNDER_CONSTRUCTION.getValidTransitions()).toBeDefined()
        - >-
          expect(AssetStatusEnum.AVAILABLE_SELF.getValidTransitions()).toBeDefined()
        - expect(AssetStatusEnum.SCRAPPED.getValidTransitions()).toHaveLength(0)
    - name: >-
        should return true for valid state transitions via canTransitionTo
        method
      assertions:
        - >-
          expect(AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF)).toBe(true)
        - >-
          expect(AssetStatusEnum.AVAILABLE_SELF.canTransitionTo(AssetStatusEnum.MAINTAINING)).toBe(true)
        - >-
          expect(AssetStatusEnum.AVAILABLE_RENT.canTransitionTo(AssetStatusEnum.FAULT)).toBe(true)
        - >-
          expect(AssetStatusEnum.AVAILABLE_IDLE.canTransitionTo(AssetStatusEnum.TEMP_CLOSED)).toBe(true)
        - >-
          expect(AssetStatusEnum.TO_BE_SCRAPPED.canTransitionTo(AssetStatusEnum.SCRAPPED)).toBe(true)
    - name: >-
        should return false for invalid state transitions via canTransitionTo
        method
      assertions:
        - >-
          expect(AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF)).toBe(false)
        - >-
          expect(AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.UNDER_CONSTRUCTION)).toBe(false)
        - >-
          expect(AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.SCRAPPED)).toBe(false)
    - name: >-
        should return correct set of valid transitions via getValidTransitions
        method
      assertions:
        - >-
          expect(AssetStatusEnum.AVAILABLE_SELF.getValidTransitions()).toContain(AssetStatusEnum.MAINTAINING)
        - >-
          expect(AssetStatusEnum.AVAILABLE_SELF.getValidTransitions()).toContain(AssetStatusEnum.FAULT)
        - >-
          expect(AssetStatusEnum.AVAILABLE_SELF.getValidTransitions()).toContain(AssetStatusEnum.TEMP_CLOSED)
        - >-
          expect(AssetStatusEnum.MAINTAINING.getValidTransitions()).toContain(AssetStatusEnum.AVAILABLE_SELF)
  e2eScenarios: []
  frameworkHint: vitest
---
# Create Asset Status Enum and Transition Validation

## Context

9-state status machine with transition validation. States cannot transition arbitrarily - certain transitions are invalid (e.g., SCRAPPED cannot change back to AVAILABLE).

## Acceptance Criteria

1. Create `AssetStatusEnum` in `com.ruoyi.asset.domain.enums`:
2. Create transition validation map in enum:
3. Add method `canTransitionTo(AssetStatusEnum target)`:
4. Add method `getValidTransitions()`:
5. Unit test `AssetStatusEnumTransitionTest`:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (9-State Status Machine)
- UX: ai/tasks/spec/UX-DESIGN.md (Status badge colors)

## Notes

Verification failed: Test strategy cannot validate Java enum from TypeScript tests - all implementation files created correctly
