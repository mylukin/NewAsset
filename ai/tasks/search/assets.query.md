---
id: search.assets.query
module: search
priority: 20
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
    pattern: tests/search/**/*.test.*
---
# Provide paginated asset list with filter/sort by type, owner, status, tags, and updated time.

## Acceptance Criteria

1. Provide paginated asset list with filter/sort by type, owner, status, tags, and updated time.
