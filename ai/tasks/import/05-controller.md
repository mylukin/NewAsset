---
id: import.controller
module: import
priority: 805
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - import.asset-import-service
  - import.template-generator
supersedes: []
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: tests/import/**/*.test.*
---
# Implement Import REST API

## Context

REST controller for Excel import and template download.

## Acceptance Criteria

1. Create `AssetImportController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add permission annotations:
4. Add @Log annotations for import operations
5. File upload validation:
6. Error responses:
## Technical Notes

- Use @RequestParam("file") MultipartFile for upload
- Reference: RuoYi file upload patterns
