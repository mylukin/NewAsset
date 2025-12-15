---
id: asset-office.controller
module: asset-office
priority: 604
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.service
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-office/**/*.test.*"
---
# Implement Office Asset REST API

## Context

REST controller for office asset operations.

## Acceptance Criteria

1. Create `AssetOfficeController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/office`

2. Implement endpoints:
   - `GET /list` - Paginated list with query parameters
   - `GET /{id}` - Get office asset detail
   - `POST /` - Create office asset
   - `PUT /{id}` - Update office asset
   - `DELETE /{ids}` - Soft delete (comma-separated IDs)
   - `POST /export` - Excel export

3. Add RuoYi permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:office:list')")
   - @PreAuthorize("@ss.hasPermi('asset:office:query')")
   - @PreAuthorize("@ss.hasPermi('asset:office:add')")
   - @PreAuthorize("@ss.hasPermi('asset:office:edit')")
   - @PreAuthorize("@ss.hasPermi('asset:office:remove')")
   - @PreAuthorize("@ss.hasPermi('asset:office:export')")

4. Add @Log annotations for audit logging

5. Request validation with @Validated

6. All endpoints return AjaxResult

## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
