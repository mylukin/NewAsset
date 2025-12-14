---
id: assets.asset.update
module: assets
priority: 19
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
# Update editable asset fields with optimistic concurrency control (updatedAt/version).

## Acceptance Criteria

1. Update editable asset fields with optimistic concurrency control (updatedAt/version).
