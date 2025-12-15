---
id: asset-house.controller
module: asset-house
priority: 204
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.service
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-house/**/*.test.*"
---
# Implement House Asset REST API

## Context

REST API for house asset CRUD operations. Supports list page and detail drawer.

## Acceptance Criteria

1. Create `AssetHouseController` in `com.ruoyi.asset.controller`:
   - Base path: `/asset/house`
   - Use @PreAuthorize for permission control

2. Implement endpoints:

   **GET /asset/house/list**
   - Query: projectId, building, floor, status, currentUsage, areaMin, areaMax, keyword, pageNum, pageSize
   - Returns: Paginated list with total count
   - Permission: asset:house:list

   **GET /asset/house/{id}**
   - Returns: Full house asset detail with attachments and recent work orders
   - Permission: asset:house:query

   **POST /asset/house**
   - Body: AssetHouseCreateDTO
   - Returns: Created asset with generated code
   - Permission: asset:house:add

   **PUT /asset/house**
   - Body: AssetHouseUpdateDTO
   - Returns: Updated asset
   - Permission: asset:house:edit

   **DELETE /asset/house/{ids}**
   - Path: Comma-separated IDs
   - Returns: Success/failure with blocked count
   - Permission: asset:house:remove

   **POST /asset/house/copy/{id}**
   - Returns: Pre-filled VO for new asset form
   - Permission: asset:house:add

   **POST /asset/house/export**
   - Body: Query filters
   - Returns: Excel file download
   - Permission: asset:house:export

3. Add @Log annotation for audit logging

4. Apply @DataScope for project-based data permission

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (RESTful patterns)
- UX: ai/tasks/spec/UX-DESIGN.md (Asset List screen)
