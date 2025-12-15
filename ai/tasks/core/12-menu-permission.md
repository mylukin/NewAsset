---
id: core.menu-permission
module: core
priority: 112
status: failing
version: 2
origin: spec-workflow
dependsOn: []
supersedes: []
tags:
  - backend
  - config
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-15T13:12:45.757Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e42939eea8649cc9be422a0171116d5f686c5107
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:05:14.730Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/menu-permission.test.ts
    e2e:
      - e2e/core/asset-menu.spec.ts
  unitTestCases:
    - name: should have valid SQL insert script at sql/asset_menu.sql
      assertions:
        - expect(fs.existsSync('sql/asset_menu.sql')).toBe(true)
        - expect(sqlContent).toContain('INSERT INTO sys_menu')
    - name: should define permission strings for asset module
      assertions:
        - 'expect(sqlContent).toMatch(/asset:\w+:\w+/)'
        - 'expect(permissions).toContain(''asset:asset:list'')'
        - 'expect(permissions).toContain(''asset:asset:query'')'
    - name: should create parent menu entry for Asset Management
      assertions:
        - expect(sqlContent).toContain('资产管理')
        - >-
          expect(menuEntries).toContainEqual(expect.objectContaining({
          menu_type: 'M' }))
    - name: should create correct menu hierarchy structure
      assertions:
        - expect(childMenus.every(m => m.parent_id)).toBe(true)
        - expect(menuStructure).toHaveProperty('children')
    - name: should set appropriate RuoYi icons for menus
      assertions:
        - expect(parentMenu.icon).toBeTruthy()
        - expect(parentMenu.icon).not.toBe('')
  e2eScenarios:
    - name: admin can see Asset Management menu after SQL applied
      steps:
        - login as admin user
        - navigate to main dashboard
        - verify Asset Management menu is visible in sidebar
    - name: admin can access asset submenus with correct permissions
      steps:
        - login as admin user
        - click Asset Management menu
        - verify submenu items are displayed
        - click submenu item
        - verify page loads without permission error
  frameworkHint: vitest
---
# Configure Menus and Permissions for Asset Module

## Context

RuoYi menu structure and permission strings for all asset management features.

## Acceptance Criteria

1. Add menu entries to sys_menu:
2. Permission strings for each module:
3. Create SQL insert script: `sql/asset_menu.sql`
4. Menu structure:
5. Set appropriate menu icons (using RuoYi icons)
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (5 roles, permission matrix)
- RuoYi sys_menu table structure
