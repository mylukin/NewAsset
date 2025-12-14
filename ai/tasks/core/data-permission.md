---
id: core.data-permission
module: core
priority: 3
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - backend
  - security
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Implement Project-Based Data Permission

## Acceptance Criteria

1. Extend RuoYi's `@DataScope` annotation to support project-based filtering:
2. Create `ProjectDataScopeHandler`:
3. Configure data permission in asset-related mappers:
4. Create project-user mapping if not exists in RuoYi base:
5. Test data isolation between projects:
