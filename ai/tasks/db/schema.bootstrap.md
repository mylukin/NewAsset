---
id: db.schema.bootstrap
module: db
priority: 11
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
# Define initial database schema for users, roles, assets, tags, attachments, AI tasks, and audit logs.

## Acceptance Criteria

1. Database schema supports both SQLite (primary) and MySQL (alternative)
2. Schema includes tables for: users, roles, assets, tags, attachments, AI tasks, and audit logs
3. Migration scripts are provided for both database types
4. Database-agnostic SQL is used where possible
