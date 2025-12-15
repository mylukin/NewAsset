---
id: core.menu-permission
module: core
priority: 112
status: passing
version: 4
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
  verifiedAt: '2025-12-15T15:08:45.434Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ddfa155ceceda0f3c6ca016ba3dc1e274d37b923
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T15:08:32.833Z'
  generatedBy: claude
  forVersion: 2
  suggestedTestFiles:
    unit:
      - tests/core/menu-permission.test.ts
    e2e: []
  unitTestCases:
    - name: should create SQL insert script at sql/asset_menu.sql
      assertions:
        - expect(fs.existsSync('sql/asset_menu.sql')).toBe(true)
        - expect(sqlContent).toBeTruthy()
    - name: should include valid INSERT statements for sys_menu table
      assertions:
        - expect(sqlContent).toMatch(/INSERT INTO.*sys_menu/i)
        - expect(menuInserts.length).toBeGreaterThan(0)
    - name: 'should define permission strings following module:action pattern'
      assertions:
        - 'expect(permissions).toContain(''asset:list'')'
        - 'expect(permission).toMatch(/^[a-z]+:[a-z]+$/)'
    - name: should have proper menu hierarchy with parent-child relationships
      assertions:
        - expect(parentMenu.menu_id).toBeDefined()
        - expect(childMenu.parent_id).toBe(parentMenu.menu_id)
    - name: should use valid RuoYi icon names for menu entries
      assertions:
        - 'expect(menuIcon).toMatch(/^[a-z-]+$/)'
        - expect(RUOYI_VALID_ICONS).toContain(menuIcon)
  e2eScenarios: []
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
