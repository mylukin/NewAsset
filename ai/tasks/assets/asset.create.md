---
id: assets.asset.create
module: assets
priority: 17
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
    pattern: tests/assets/**/*.test.*
---
# Create an asset with server-side validation and persist it to the database.

## Acceptance Criteria

1. Create an asset with server-side validation and persist it to the database.
