---
id: office.mapper
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn:
  - office.entity
supersedes: []
tags:
  - backend
  - mapper
  - P0
testRequirements:
  unit:
    required: true
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Mapper

## Acceptance Criteria

1. Create `OfficeAssetMapper` interface
2. Create `OfficeAssetMapper.xml`
3. Implement query methods:
4. Implement write methods:
5. Support joined queries with base asset table
