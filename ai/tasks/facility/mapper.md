---
id: facility.mapper
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn:
  - facility.entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/facility/**/*.test.*
---
# Create Facility Asset Mapper

## Acceptance Criteria

1. Create `FacilityAssetMapper` interface
2. Create `FacilityAssetMapper.xml` with SQL mappings
3. Implement query methods:
4. Implement write methods:
5. Support joined queries with base asset table
6. Include data permission placeholder
