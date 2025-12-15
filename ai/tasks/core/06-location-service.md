---
id: core.location-service
module: core
priority: 106
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Implement Location Hierarchy Service

## Context

3-level location hierarchy (Building → Floor → Unit) management. Supports the cascader component in frontend forms.

## Acceptance Criteria

1. Create `AssetLocation` entity in `com.ruoyi.asset.domain.entity`:
   - Fields: id, parentId, projectId, level, code, name, sortOrder
   - Audit fields from BaseEntity

2. Create `AssetLocationMapper` interface:
   - selectByProjectId(Long projectId)
   - selectChildrenByParentId(Long parentId)
   - insert, update, delete

3. Create `AssetLocationService` in `com.ruoyi.asset.service`:
   - `getTreeByProject(Long projectId)`: Returns hierarchical tree structure
   - `addLocation(AssetLocation location)`: Add new building/floor/unit
   - `updateLocation(AssetLocation location)`: Update existing
   - `deleteLocation(Long id)`: Delete (check for children and linked assets)

4. Tree structure response format:
   ```json
   [
     {
       "id": 1, "code": "A", "name": "A栋", "level": 1,
       "children": [
         {
           "id": 2, "code": "1F", "name": "1层", "level": 2,
           "children": [
             {"id": 3, "code": "101", "name": "101室", "level": 3}
           ]
         }
       ]
     }
   ]
   ```

5. Validation:
   - Level 1 (building) has no parent
   - Level 2 (floor) must have level 1 parent
   - Level 3 (unit) must have level 2 parent

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (3-level hierarchy decision)
- UX: ai/tasks/spec/UX-DESIGN.md (Cascader component)
