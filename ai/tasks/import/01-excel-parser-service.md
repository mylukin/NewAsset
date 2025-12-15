---
id: import.excel-parser-service
module: import
priority: 801
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.asset-entity
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/import/**/*.test.*"
---
# Implement Excel Parser Service

## Context

Core service for parsing Excel files using EasyExcel library.

## Acceptance Criteria

1. Add EasyExcel 3.1.x dependency to pom.xml:
   ```xml
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>easyexcel</artifactId>
       <version>3.1.5</version>
   </dependency>
   ```

2. Create `ExcelParserService`:
   - parseExcel(InputStream, Class<T> rowClass) - generic parser
   - Returns List<T> with row data
   - Supports header mapping via @ExcelProperty annotations

3. Create base row classes in `com.ruoyi.asset.domain.excel`:
   - `BaseAssetImportRow` - common fields (name, projectCode, location, etc.)
   - @ExcelProperty annotations for column headers

4. Implement error handling:
   - Empty file detection
   - Invalid format detection (not xlsx/xls)
   - Missing required columns
   - Row count limit (max 5000 rows per import)

5. File size validation:
   - Max file size: 10MB
   - Return early if exceeded

## Technical Notes

- Reference: EasyExcel documentation for RuoYi integration
- Use streaming mode for large files
