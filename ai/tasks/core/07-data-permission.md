---
id: core.data-permission
module: core
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [security, permission]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Project-Based Data Permission

## Context

Data access is controlled at the project level. Users can only view/manage assets within their authorized projects. This extends RuoYi's data scope mechanism.

## Acceptance Criteria

1. Project dimension added to data scope:
   - Extend `@DataScope` annotation or create `@AssetDataScope`
   - Map department/custom scope to project_id filtering
2. All asset queries include project filter:
   - SQL includes `${params.dataScope}` condition
   - Mapper XML properly configured
3. Permission annotations configured:
   - `asset:house:*` for house module
   - `asset:parking:*` for parking module
   - `asset:facility:*` for facility module
   - `asset:venue:*` for venue module
   - `asset:office:*` for office module
   - `asset:maint:*` for maintenance module
4. Menu structure registered in sys_menu:
   - Asset Management parent menu
   - Sub-menus for each module
   - Button permissions (add, edit, delete, export)
5. Role-based access verified:
   - Asset Manager sees authorized projects
   - Operations staff sees assigned work orders
   - Management has read-only cross-project view

## Technical Notes

- Reference: RuoYi DataScopeAspect.java
- Use sys_role_dept or custom sys_role_project table
- SQL pattern: `AND a.project_id IN (SELECT ...)`
