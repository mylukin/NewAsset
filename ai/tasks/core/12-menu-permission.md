---
id: core.menu-permission
module: core
priority: 112
status: passing
version: 3
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
  verifiedAt: '2025-12-15T13:05:04.282Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e42939eea8649cc9be422a0171116d5f686c5107
  summary: 5/5 criteria satisfied
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
