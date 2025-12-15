---
id: maintenance.order-number-generator
module: maintenance
priority: 704
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.database-schema
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Implement Work Order Number Generator

## Context

Date-based work order number generator with format WO-YYYYMMDD-NNN.

## Acceptance Criteria

1. Create `MaintOrderNoGenerator` service:
2. Implement optimistic locking for concurrent generation:
3. Handle date rollover:
4. Add error handling:
5. Unit tests:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Date-based WO number)
- Similar pattern to core.code-generator but date-based
