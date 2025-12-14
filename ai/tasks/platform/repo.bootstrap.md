---
id: platform.repo.bootstrap
module: platform
priority: 10
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
    pattern: tests/platform/**/*.test.*
---
# Create a pnpm workspace with consistent scripts (dev/build/test) and TypeScript base config.

## Acceptance Criteria

1. Create a pnpm workspace with consistent scripts (dev/build/test) and TypeScript base config.
