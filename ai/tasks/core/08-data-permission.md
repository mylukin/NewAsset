---
id: core.data-permission
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [backend, security]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Implement Project-Based Data Permission

## Context

Data access control is based on project dimension. Users can only view/edit assets within their authorized projects.

## Acceptance Criteria

1. Extend RuoYi's `@DataScope` annotation to support project-based filtering:
   - Map department/custom scope to project_id dimension

2. Create `ProjectDataScopeHandler`:
   - Inject project filter conditions into SQL queries
   - Support multiple project assignments per user

3. Configure data permission in asset-related mappers:
   - Add `${params.dataScope}` placeholder in SQL WHERE clauses
   - Example: `WHERE a.del_flag = '0' AND ${params.dataScope}`

4. Create project-user mapping if not exists in RuoYi base:
   - Consider using sys_user_dept with project as department
   - Or create separate `t_user_project` mapping table

5. Test data isolation between projects:
   - User A in Project X cannot see Project Y assets
   - Admin can see all projects

## Technical Notes

- Reference: TECH.md section 5.4.2
- Follow RuoYi's DataScope pattern
