---
id: import.controller
module: import
priority: 805
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - import.asset-import-service
  - import.template-generator
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/import/**/*.test.*"
---
# Implement Import REST API

## Context

REST controller for Excel import and template download.

## Acceptance Criteria

1. Create `AssetImportController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/import`

2. Implement endpoints:
   - `POST /{assetType}` - Import Excel file
     - Accepts multipart/form-data
     - assetType: house, parking, facility, venue, office
     - Optional: importMode (valid_only, all_or_nothing)
     - Returns ImportResult

   - `GET /{assetType}/template` - Download template
     - Returns Excel file (application/vnd.openxmlformats-officedocument.spreadsheetml.sheet)
     - Sets Content-Disposition header for download

   - `POST /{assetType}/validate` - Validate without importing
     - Returns ValidationResult only
     - For preview before actual import

3. Add permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:{type}:import')")
   - Where {type} is the asset type

4. Add @Log annotations for import operations

5. File upload validation:
   - Max file size: 10MB
   - Allowed extensions: .xlsx, .xls
   - Return 400 for invalid files

6. Error responses:
   - 400: Invalid file format
   - 413: File too large
   - 422: Validation errors (with details)

## Technical Notes

- Use @RequestParam("file") MultipartFile for upload
- Reference: RuoYi file upload patterns
