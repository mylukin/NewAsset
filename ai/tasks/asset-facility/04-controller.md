---
id: asset-facility.controller
module: asset-facility
priority: 404
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.service
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-facility/**/*.test.*"
---
# Implement Facility Asset REST API

## Context

REST controller for facility asset operations.

## Acceptance Criteria

1. Create `AssetFacilityController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/facility`

2. Implement endpoints:
   - `GET /list` - Paginated list with query parameters
   - `GET /{id}` - Get facility detail
   - `POST /` - Create facility asset
   - `PUT /{id}` - Update facility asset
   - `DELETE /{ids}` - Soft delete (comma-separated IDs)
   - `POST /export` - Excel export

3. Add RuoYi permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:facility:list')")
   - @PreAuthorize("@ss.hasPermi('asset:facility:query')")
   - @PreAuthorize("@ss.hasPermi('asset:facility:add')")
   - @PreAuthorize("@ss.hasPermi('asset:facility:edit')")
   - @PreAuthorize("@ss.hasPermi('asset:facility:remove')")
   - @PreAuthorize("@ss.hasPermi('asset:facility:export')")

4. Add @Log annotations for audit logging

5. Request validation with @Validated

6. All endpoints return AjaxResult

## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
