---
id: import.asset-import-service
module: import
priority: 803
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - import.row-validator-service
  - core.code-generator
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/import/**/*.test.*"
---
# Implement Asset Import Service

## Context

Service for batch importing validated asset data.

## Acceptance Criteria

1. Create `IAssetImportService` interface:
   - importAssets(InputStream file, AssetType type, Long projectId)
   - Returns ImportResult

2. Create `ImportResult`:
   - importedCount: int
   - skippedCount: int
   - errorCount: int
   - errors: List<RowError>
   - importedAssetIds: List<Long>

3. Create `AssetImportServiceImpl`:
   - Parse Excel using ExcelParserService
   - Validate using ImportRowValidator
   - For valid rows: generate asset codes, create assets in batch
   - Use @Transactional with rollback on failure option

4. Implement batch insert:
   - Use MyBatis batch insert (500 rows per batch)
   - Generate asset codes in sequence
   - Create base asset + extension table records

5. Support "import only valid rows" mode:
   - Skip invalid rows, import valid ones
   - Return detailed error report for skipped rows

6. Support "all or nothing" mode:
   - If any error, rollback entire import

## Technical Notes

- Reference: core.code-generator for asset code generation
- Use batch operations for performance (<30s for 1000 rows)
