---
id: house.controller
module: house
priority: 13
status: failing
version: 17
origin: manual
dependsOn:
  - house.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Controller

## Context

RESTful API controller for house asset management, following RuoYi conventions.

## Acceptance Criteria

1. Create `AssetHouseController` in `com.ruoyi.asset.controller`
2. Implement endpoints:
   - `GET /asset/house/list` - paginated list
   - `GET /asset/house/{id}` - get detail
   - `POST /asset/house` - create new house asset
   - `PUT /asset/house` - update house asset
   - `DELETE /asset/house/{ids}` - delete (soft)
   - `POST /asset/house/export` - export to Excel
   - `POST /asset/house/{id}/copy` - copy asset
3. Add permission annotations: `@PreAuthorize("@ss.hasPermi('asset:house:list')")`
4. Add operation logging: `@Log(title = "房源资产", businessType = BusinessType.INSERT)`
5. Return standard RuoYi response format (AjaxResult, TableDataInfo)
6. Add Swagger/OpenAPI documentation annotations

## Technical Notes

- Reference: TECH.md Section 6.4
- Pattern: RuoYi BaseController extension
- Permissions: asset:house:list, asset:house:query, asset:house:add, asset:house:edit, asset:house:remove, asset:house:export
