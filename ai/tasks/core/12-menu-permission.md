---
id: core.menu-permission
module: core
priority: 112
status: failing
version: 1
origin: spec-workflow
dependsOn: []
tags:
  - backend
  - config
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Configure Menus and Permissions for Asset Module

## Context

RuoYi menu structure and permission strings for all asset management features.

## Acceptance Criteria

1. Add menu entries to sys_menu:

   **Asset Management** (root menu)
   - 房源资产 (asset:house:*)
   - 车库资产 (asset:parking:*)
   - 配套资产 (asset:facility:*)
   - 场馆资产 (asset:venue:*)
   - 办公资产 (asset:office:*)
   - 维护工单 (asset:maint:*)
   - 位置管理 (asset:location:*)

2. Permission strings for each module:
   - {module}:list - View list
   - {module}:query - View detail
   - {module}:add - Create
   - {module}:edit - Update
   - {module}:remove - Delete
   - {module}:export - Export Excel
   - {module}:import - Import Excel

3. Create SQL insert script: `sql/asset_menu.sql`

4. Menu structure:
   ```
   资产管理
   ├── 房源资产
   ├── 车库资产
   ├── 配套资产
   ├── 场馆资产
   ├── 办公资产
   ├── 维护工单
   └── 系统配置
       └── 位置管理
   ```

5. Set appropriate menu icons (using RuoYi icons)

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (5 roles, permission matrix)
- RuoYi sys_menu table structure
