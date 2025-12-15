---
id: import.asset-import-service
module: import
priority: 803
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - import.row-validator-service
  - core.code-generator
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/import/**/*.test.*
---
# Implement Asset Import Service

## Context

Service for batch importing validated asset data.

## Acceptance Criteria

1. Create `IAssetImportService` interface:
2. Create `ImportResult`:
3. Create `AssetImportServiceImpl`:
4. Implement batch insert:
5. Support "import only valid rows" mode:
6. Support "all or nothing" mode:
## Technical Notes

- Reference: core.code-generator for asset code generation
- Use batch operations for performance (<30s for 1000 rows)
