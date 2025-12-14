---
id: core.asset-status-enum
module: core
priority: 4
status: failing
version: 5
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
