---
id: asset-venue.controller
module: asset-venue
priority: 504
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.service
tags:
  - backend
  - controller
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-venue/**/*.test.*"
---
# Implement Venue Asset REST API

## Context

REST controller for venue asset operations.

## Acceptance Criteria

1. Create `AssetVenueController` in `com.ruoyi.asset.controller`:
   - Base path: `/api/asset/venue`

2. Implement endpoints:
   - `GET /list` - Paginated list with query parameters
   - `GET /{id}` - Get venue detail
   - `POST /` - Create venue asset
   - `PUT /{id}` - Update venue asset
   - `DELETE /{ids}` - Soft delete (comma-separated IDs)
   - `POST /export` - Excel export

3. Add RuoYi permission annotations:
   - @PreAuthorize("@ss.hasPermi('asset:venue:list')")
   - @PreAuthorize("@ss.hasPermi('asset:venue:query')")
   - @PreAuthorize("@ss.hasPermi('asset:venue:add')")
   - @PreAuthorize("@ss.hasPermi('asset:venue:edit')")
   - @PreAuthorize("@ss.hasPermi('asset:venue:remove')")
   - @PreAuthorize("@ss.hasPermi('asset:venue:export')")

4. Add @Log annotations for audit logging

5. Request validation with @Validated

6. All endpoints return AjaxResult

## Technical Notes

- Reference: RuoYi standard controller patterns
- Use @DataScope for project-level data permissions
