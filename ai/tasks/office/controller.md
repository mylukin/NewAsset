---
id: office.controller
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn:
  - office.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Controller

## Acceptance Criteria

1. Create `OfficeAssetController`:
2. Implement endpoints:
3. Add permission annotations:
4. Add `@Log` annotations
