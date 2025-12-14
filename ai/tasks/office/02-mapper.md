---
id: office.mapper
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn: [office.entity]
supersedes: []
tags: [backend, mapper, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/office/**/*.test.*"
---
# Create Office Asset Mapper

## Context

Database access for office asset CRUD operations.

## Acceptance Criteria

1. Create `OfficeAssetMapper` interface

2. Create `OfficeAssetMapper.xml`

3. Implement query methods:
   - `selectOfficeAssetList(OfficeAssetQueryDTO query)` - filters: project, useDeptId, officeType, dutyUserId (责任人)
   - `selectOfficeAssetById(Long assetId)`
   - `selectOfficeAssetByCode(String assetCode)`
   - `selectOfficeAssetBySerialNo(String serialNo)` - for IT asset lookup

4. Implement write methods:
   - `insertOfficeAsset(OfficeAsset office)`
   - `updateOfficeAsset(OfficeAsset office)`
   - `deleteOfficeAssetByAssetId(Long assetId)`

5. Support joined queries with base asset table

## Technical Notes

- Similar pattern to other asset mappers
