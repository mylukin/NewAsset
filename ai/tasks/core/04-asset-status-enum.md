---
id: core.asset-status-enum
module: core
priority: 4
status: passing
version: 22
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - enum
  - state-machine
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T11:59:07.301Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: c5f1f9bc1afea8c5cdfe08bb8b33f70757be370c
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:58:44.456Z'
  generatedBy: claude
  forVersion: 7
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/domain/enums/AssetStatusEnumTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/domain/enums/AssetTypeEnumTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/domain/enums/MaintOrderStatusEnumTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/domain/enums/MaintOperationTypeTest.java
    e2e: []
  unitTestCases:
    - name: AssetStatusEnum should contain all required status values
      assertions:
        - 'assertEquals(9, AssetStatusEnum.values().length)'
        - assertNotNull(AssetStatusEnum.UNDER_CONSTRUCTION)
        - assertNotNull(AssetStatusEnum.AVAILABLE_SELF)
        - assertNotNull(AssetStatusEnum.AVAILABLE_RENT)
        - assertNotNull(AssetStatusEnum.AVAILABLE_IDLE)
        - assertNotNull(AssetStatusEnum.TEMP_CLOSED)
        - assertNotNull(AssetStatusEnum.FAULT)
        - assertNotNull(AssetStatusEnum.MAINTAINING)
        - assertNotNull(AssetStatusEnum.TO_BE_SCRAPPED)
        - assertNotNull(AssetStatusEnum.SCRAPPED)
    - name: AssetTypeEnum should contain all five asset types
      assertions:
        - 'assertEquals(5, AssetTypeEnum.values().length)'
        - assertNotNull(AssetTypeEnum.HOUSE)
        - assertNotNull(AssetTypeEnum.PARKING)
        - assertNotNull(AssetTypeEnum.FACILITY)
        - assertNotNull(AssetTypeEnum.VENUE)
        - assertNotNull(AssetTypeEnum.OFFICE)
    - name: MaintOrderStatusEnum should contain all maintenance order statuses
      assertions:
        - 'assertEquals(6, MaintOrderStatusEnum.values().length)'
        - assertNotNull(MaintOrderStatusEnum.WAIT_ASSIGN)
        - assertNotNull(MaintOrderStatusEnum.WAIT_ACCEPT)
        - assertNotNull(MaintOrderStatusEnum.PROCESSING)
        - assertNotNull(MaintOrderStatusEnum.WAIT_CONFIRM)
        - assertNotNull(MaintOrderStatusEnum.CLOSED)
        - assertNotNull(MaintOrderStatusEnum.CANCELED)
    - name: MaintOperationType should contain all operation types
      assertions:
        - 'assertEquals(8, MaintOperationType.values().length)'
        - assertNotNull(MaintOperationType.CREATE)
        - assertNotNull(MaintOperationType.ASSIGN)
        - assertNotNull(MaintOperationType.ACCEPT)
        - assertNotNull(MaintOperationType.START_HANDLE)
        - assertNotNull(MaintOperationType.COMPLETE)
        - assertNotNull(MaintOperationType.CONFIRM)
        - assertNotNull(MaintOperationType.REJECT)
        - assertNotNull(MaintOperationType.CANCEL)
    - name: Each enum should have code and description fields with getters
      assertions:
        - >-
          assertEquals("UNDER_CONSTRUCTION",
          AssetStatusEnum.UNDER_CONSTRUCTION.getCode())
        - assertNotNull(AssetStatusEnum.UNDER_CONSTRUCTION.getDescription())
        - 'assertEquals("HOUSE", AssetTypeEnum.HOUSE.getCode())'
        - assertNotNull(AssetTypeEnum.HOUSE.getDescription())
        - >-
          assertEquals("WAIT_ASSIGN",
          MaintOrderStatusEnum.WAIT_ASSIGN.getCode())
        - assertNotNull(MaintOrderStatusEnum.WAIT_ASSIGN.getDescription())
        - 'assertEquals("CREATE", MaintOperationType.CREATE.getCode())'
        - assertNotNull(MaintOperationType.CREATE.getDescription())
    - name: fromCode utility method should return correct enum for valid code
      assertions:
        - >-
          assertEquals(AssetStatusEnum.AVAILABLE_RENT,
          AssetStatusEnum.fromCode("AVAILABLE_RENT"))
        - 'assertEquals(AssetTypeEnum.PARKING, AssetTypeEnum.fromCode("PARKING"))'
        - >-
          assertEquals(MaintOrderStatusEnum.PROCESSING,
          MaintOrderStatusEnum.fromCode("PROCESSING"))
        - >-
          assertEquals(MaintOperationType.COMPLETE,
          MaintOperationType.fromCode("COMPLETE"))
    - name: fromCode utility method should return null for invalid code
      assertions:
        - assertNull(AssetStatusEnum.fromCode("INVALID_CODE"))
        - assertNull(AssetTypeEnum.fromCode("INVALID_CODE"))
        - assertNull(MaintOrderStatusEnum.fromCode("INVALID_CODE"))
        - assertNull(MaintOperationType.fromCode("INVALID_CODE"))
  e2eScenarios: []
  frameworkHint: junit4
---
# Create Asset Status Enum and State Machine Enums

## Context

The system uses a unified asset status machine with defined states and valid transitions. Enums provide type-safe status handling.

## Acceptance Criteria

1. Create `AssetStatusEnum` with all states: UNDER_CONSTRUCTION, AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE, TEMP_CLOSED, FAULT, MAINTAINING, TO_BE_SCRAPPED, SCRAPPED
2. Create `AssetTypeEnum`: HOUSE, PARKING, FACILITY, VENUE, OFFICE
3. Create `MaintOrderStatusEnum`: WAIT_ASSIGN, WAIT_ACCEPT, PROCESSING, WAIT_CONFIRM, CLOSED, CANCELED
4. Create `MaintOperationType`: CREATE, ASSIGN, ACCEPT, START_HANDLE, COMPLETE, CONFIRM, REJECT, CANCEL
5. Each enum should have code and description fields
6. Include utility methods for code/description lookup
7. Register enums with dictionary system if applicable
## Technical Notes

- Reference: TECH.md Section 5.2.1, PRD Section 4.4
- Pattern: Java enum with code/desc pattern
- Location: `com.ruoyi.asset.domain.enums`
