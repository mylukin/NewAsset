---
id: core.status-enum
module: core
priority: 103
status: failed
version: 3
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
  verifiedAt: '2025-12-15T12:14:24.063Z'
  verdict: fail
  verifiedBy: strategy-framework
  commitHash: 935327b8ce8950c9f34067c8d846e00c5b4b3442
  summary: 0/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T12:14:06.007Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/AssetStatusEnumTransition.test.ts
    e2e: []
  unitTestCases:
    - name: should create AssetStatusEnum with all required status values
      assertions:
        - expect(Object.keys(AssetStatusEnum)).toContain('IN_USE')
        - expect(Object.keys(AssetStatusEnum)).toContain('IDLE')
        - expect(Object.keys(AssetStatusEnum)).toContain('MAINTENANCE')
        - expect(Object.keys(AssetStatusEnum)).toContain('RETIRED')
        - expect(AssetStatusEnum.IN_USE).toBeDefined()
        - expect(AssetStatusEnum.IDLE).toBeDefined()
    - name: should define valid transition map in AssetStatusEnum
      assertions:
        - expect(AssetStatusEnum.transitionMap).toBeDefined()
        - expect(AssetStatusEnum.transitionMap).toBeInstanceOf(Object)
        - >-
          expect(AssetStatusEnum.transitionMap[AssetStatusEnum.IN_USE]).toContain(AssetStatusEnum.IDLE)
        - >-
          expect(AssetStatusEnum.transitionMap[AssetStatusEnum.IN_USE]).toContain(AssetStatusEnum.MAINTENANCE)
        - >-
          expect(AssetStatusEnum.transitionMap[AssetStatusEnum.IDLE]).toContain(AssetStatusEnum.IN_USE)
    - name: should validate transitions using canTransitionTo method
      assertions:
        - >-
          expect(AssetStatusEnum.IN_USE.canTransitionTo(AssetStatusEnum.IDLE)).toBe(true)
        - >-
          expect(AssetStatusEnum.IN_USE.canTransitionTo(AssetStatusEnum.MAINTENANCE)).toBe(true)
        - >-
          expect(AssetStatusEnum.IN_USE.canTransitionTo(AssetStatusEnum.RETIRED)).toBe(true)
        - >-
          expect(AssetStatusEnum.IDLE.canTransitionTo(AssetStatusEnum.IN_USE)).toBe(true)
        - >-
          expect(AssetStatusEnum.IDLE.canTransitionTo(AssetStatusEnum.IN_USE)).toBe(true)
        - >-
          expect(AssetStatusEnum.IDLE.canTransitionTo(AssetStatusEnum.IN_USE)).toBe(false)
    - name: should return valid transitions using getValidTransitions method
      assertions:
        - >-
          expect(AssetStatusEnum.IN_USE.getValidTransitions()).toEqual([AssetStatusEnum.IDLE,
          AssetStatusEnum.MAINTENANCE, AssetStatusEnum.RETIRED])
        - >-
          expect(AssetStatusEnum.IDLE.getValidTransitions()).toEqual([AssetStatusEnum.IN_USE])
        - >-
          expect(AssetStatusEnum.MAINTENANCE.getValidTransitions()).toEqual([AssetStatusEnum.IDLE,
          AssetStatusEnum.IN_USE])
        - expect(AssetStatusEnum.RETIRED.getValidTransitions()).toHaveLength(0)
        - >-
          expect(AssetStatusEnum.IN_USE.getValidTransitions()).toContain(AssetStatusEnum.IDLE)
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
