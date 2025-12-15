---
id: import.excel-parser-service
module: import
priority: 801
status: failing
version: 2
origin: spec-workflow
dependsOn:
  - core.asset-entity
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/import/**/*.test.*
---
# Implement Excel Parser Service

## Context

Core service for parsing Excel files using EasyExcel library.

## Acceptance Criteria

1. Add EasyExcel 3.1.x dependency to pom.xml:
2. Create `ExcelParserService`:
3. Create base row classes in `com.ruoyi.asset.domain.excel`:
4. Implement error handling:
5. File size validation:
## Technical Notes

- Reference: EasyExcel documentation for RuoYi integration
- Use streaming mode for large files
