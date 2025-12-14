---
id: assets.asset.delete
module: assets
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
    pattern: tests/assets/**/*.test.*
---
# Soft-delete an asset and prevent it from appearing in default listings.

## Acceptance Criteria

1. Soft-delete an asset and prevent it from appearing in default listings.
