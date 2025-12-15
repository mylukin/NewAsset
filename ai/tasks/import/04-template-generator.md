---
id: import.template-generator
module: import
priority: 804
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
# Implement Template Generator Service

## Context

Service for generating Excel import templates for each asset type.

## Acceptance Criteria

1. Create `TemplateGeneratorService`:
2. Create template row classes:
3. Common template columns:
4. Type-specific columns added per asset type:
5. Include sample rows (1-2) showing valid data format
6. Add data validation dropdowns for dictionary fields (if supported)
## Technical Notes

- Use EasyExcel write API for template generation
- Chinese column headers with English mapping
