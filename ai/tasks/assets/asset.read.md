---
id: assets.asset.read
module: assets
priority: 18
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
# Fetch an asset by id with authorization checks and consistent error responses.

## Acceptance Criteria

1. Fetch an asset by id with authorization checks and consistent error responses.
