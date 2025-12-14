---
id: facility.service
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn:
  - facility.mapper
  - core.code-generator
supersedes: []
tags:
  - backend
  - service
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Service

## Acceptance Criteria

1. Create `IFacilityAssetService` interface:
2. Create `FacilityAssetServiceImpl`
3. Implement CRUD with code generation and transaction support
4. Implement warranty expiry check:
