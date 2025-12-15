---
id: import.row-validator-service
module: import
priority: 802
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - import.excel-parser-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/import/**/*.test.*"
---
# Implement Row Validation Service

## Context

Service for validating parsed Excel rows before import.

## Acceptance Criteria

1. Create `ImportRowValidator`:
   - validate(List<T> rows, AssetType type) - validate all rows
   - Returns ValidationResult with errors per row

2. Create `ValidationResult`:
   - totalRows: int
   - validRows: int
   - invalidRows: int
   - errors: List<RowError>
   - validData: List<T> - rows that passed validation

3. Create `RowError`:
   - rowNumber: int
   - column: String
   - value: String
   - errorMessage: String

4. Implement validation rules:
   - Required fields check (name, type, etc.)
   - Data type validation (numbers, dates)
   - Dictionary value validation (status, type codes)
   - Location code validation (exists in t_asset_location)
   - Duplicate check within file (e.g., duplicate names)

5. Collect all errors per row (don't stop at first error)

6. Return validation result without persisting data

## Technical Notes

- Use JSR-380 @Valid annotations where possible
- Custom validators for business rules
