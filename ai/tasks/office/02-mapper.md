---
id: office.mapper
module: office
priority: 49
status: failing
version: 14
origin: manual
dependsOn:
  - office.entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Mapper

## Context

MyBatis mapper for office asset CRUD operations.

## Acceptance Criteria

1. Create `AssetOfficeMapper` interface
2. Create XML mapper file
3. Implement methods:
   - selectAssetOfficeList, selectAssetOfficeById
   - insertAssetOffice, updateAssetOffice, deleteAssetOfficeByIds
4. Support filters: projectId, useDeptId, officeType, dutyUserId, status
5. Include data scope filtering
6. Join with base asset table

## Technical Notes

- Reference: TECH.md Section 4.1.2
- Pattern: MyBatis XML mapper
- Location: `resources/mapper/asset/AssetOfficeMapper.xml`
