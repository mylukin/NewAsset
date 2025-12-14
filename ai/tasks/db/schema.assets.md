---
id: db.schema.assets
module: db
priority: 12
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
# Define database schema for assets (name, type, tags, owner, status, timestamps) with migrations.

## Acceptance Criteria

1. Asset table schema supports both SQLite (primary) and MySQL (alternative)
2. Fields include: name, type, tags, owner, status, timestamps
3. Migration scripts work on both database types
4. Proper indexes are created for common queries
