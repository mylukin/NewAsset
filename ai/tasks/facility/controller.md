---
id: facility.controller
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn:
  - facility.service
supersedes: []
tags:
  - backend
  - controller
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Controller

## Acceptance Criteria

1. Create `FacilityAssetController`:
2. Implement endpoints:
3. Add permission annotations:
4. Add `@Log` annotations for audit
