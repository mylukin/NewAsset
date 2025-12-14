---
id: house.controller
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [house.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/house/**/*.test.*"
---
# Create House Asset Controller

## Context

The controller exposes RESTful API endpoints for house asset operations.

## Acceptance Criteria

1. Create `HouseAssetController` in `com.ruoyi.asset.controller`:
   - Base path: `/asset/house`

2. Implement endpoints:

   **List** - `GET /asset/house/list`
   - Parameters: projectId, building, floor, status, ownershipType, areaMin, areaMax, pageNum, pageSize
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:list')")`
   - Returns: paginated TableDataInfo

   **Detail** - `GET /asset/house/{id}`
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:query')")`
   - Returns: AjaxResult with HouseAssetVO

   **Create** - `POST /asset/house`
   - Request body: HouseAssetCreateDTO
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:add')")`
   - Add `@Log` annotation for audit
   - Returns: AjaxResult with new asset ID

   **Update** - `PUT /asset/house`
   - Request body: HouseAssetUpdateDTO
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:edit')")`
   - Add `@Log` annotation

   **Delete** - `DELETE /asset/house/{ids}`
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:remove')")`
   - Add `@Log` annotation

   **Export** - `POST /asset/house/export`
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:export')")`
   - Returns: Excel file download

   **Copy** - `POST /asset/house/{id}/copy`
   - Permission: `@PreAuthorize("@ss.hasPermi('asset:house:add')")`
   - For same-floor duplication

3. Add request validation using `@Validated`

## Technical Notes

- Reference: TECH.md section 6.1, 6.4
- Follow RuoYi controller conventions
