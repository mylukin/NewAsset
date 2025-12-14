---
id: office.service
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn:
  - office.mapper
  - core.code-generator
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Service

## Acceptance Criteria

1. Create `IOfficeAssetService` interface:
2. Create `OfficeAssetServiceImpl`
3. Implement condition statistics (P1):
