---
id: core.location-controller
module: core
priority: 109
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.location-service
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Implement Location Hierarchy API

## Context

REST API for location hierarchy management. Supports cascader component for building/floor/unit selection.

## Acceptance Criteria

1. Create `AssetLocationController` in `com.ruoyi.asset.controller`:
   - Base path: `/asset/location`
   - Use @PreAuthorize for permission control

2. Implement endpoints:

   **GET /asset/location/tree**
   - Query param: projectId (required)
   - Returns: Hierarchical tree structure
   - Permission: authenticated users
   ```json
   {
     "code": 200,
     "data": [
       {"id": 1, "name": "A栋", "children": [...]}
     ]
   }
   ```

   **POST /asset/location**
   - Body: {parentId, projectId, level, code, name}
   - Returns: Created location
   - Permission: asset:location:add

   **PUT /asset/location**
   - Body: {id, code, name, sortOrder}
   - Returns: Updated location
   - Permission: asset:location:edit

   **DELETE /asset/location/{id}**
   - Returns: Success/failure
   - Permission: asset:location:remove
   - Error if has children or linked assets

3. Add @Log annotation for audit logging

4. Validation:
   - projectId required for tree query
   - level must be 1, 2, or 3
   - code and name required

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (3-level hierarchy)
- UX: Cascader needs tree structure response
