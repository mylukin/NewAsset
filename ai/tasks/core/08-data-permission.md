---
id: core.data-permission
module: core
priority: 8
status: failing
version: 6
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - security
  - permission
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Configure Data Permission by Project

## Context

Asset data must be filtered by project based on user's data scope. RuoYi's @DataScope annotation needs customization to support project-level filtering.

## Acceptance Criteria

1. Extend RuoYi DataScope to support `project_id` field filtering
2. Create or configure `sys_project` table if not existing (or use dept as project proxy)
3. Define data scope SQL patterns for asset queries:
   - Full access: No filter
   - Project-based: `AND project_id IN (user's authorized projects)`
   - Self only: `AND create_by = #{userId}`
4. Apply data scope to asset list queries via `${params.dataScope}` placeholder
5. Configure role-based data scope assignments:
   - System Admin: Full access
   - Asset Manager: Authorized projects
   - Operations: Assigned orders only
6. Document data permission configuration for administrators

## Technical Notes

- Reference: TECH.md Section 5.4, RuoYi DataScope mechanism
- Pattern: AOP-based data filtering
- Integration: Mapper XML with dataScope placeholder
