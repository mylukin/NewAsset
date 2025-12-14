---
id: tests.coverage.smoke
module: tests
priority: 29
status: failing
version: 1
origin: init-auto
dependsOn: []
supersedes: []
tags:
  - inferred
testRequirements:
  unit:
    required: false
    pattern: tests/tests/**/*.test.*
---
# Add unit and integration tests covering asset CRUD, RBAC enforcement, and AI task state transitions.

## Acceptance Criteria

1. Add unit and integration tests covering asset CRUD, RBAC enforcement, and AI task state transitions.
