---
id: db.schema.users
module: db
priority: 13
status: failing
version: 1
origin: init-auto
dependsOn: []
supersedes: []
tags:
  - inferred
testRequirements:
  unit:
    required: false
    pattern: tests/db/**/*.test.*
---
# Define users/roles tables to support authentication and authorization.

## Acceptance Criteria

1. Users and roles tables support both SQLite (primary) and MySQL (alternative)
2. Tables support authentication (password hash, tokens) and authorization (roles, permissions)
3. Migration scripts are provided for both database types
4. Foreign key relationships are properly defined
