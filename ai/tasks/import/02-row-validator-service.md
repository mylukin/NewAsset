---
id: import.row-validator-service
module: import
priority: 802
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - import.excel-parser-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/import/**/*.test.*
---
# Implement Row Validation Service

## Context

Service for validating parsed Excel rows before import.

## Acceptance Criteria

1. Create `ImportRowValidator`:
2. Create `ValidationResult`:
3. Create `RowError`:
4. Implement validation rules:
5. Collect all errors per row (don't stop at first error)
6. Return validation result without persisting data
## Technical Notes

- Use JSR-380 @Valid annotations where possible
- Custom validators for business rules
