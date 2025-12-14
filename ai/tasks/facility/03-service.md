---
id: facility.service
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn: [facility.mapper, core.code-generator]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/facility/**/*.test.*"
---
# Create Facility Asset Service

## Context

The service layer handles business logic for facility assets.

## Acceptance Criteria

1. Create `IFacilityAssetService` interface:
   - `List<FacilityAssetVO> selectFacilityAssetList(FacilityAssetQueryDTO query)`
   - `FacilityAssetVO selectFacilityAssetById(Long assetId)`
   - `Long insertFacilityAsset(FacilityAssetCreateDTO dto)`
   - `int updateFacilityAsset(FacilityAssetUpdateDTO dto)`
   - `int deleteFacilityAssetByIds(Long[] assetIds)`
   - `List<FacilityAssetVO> selectExpiringWarranty(int days)` - for P1 reminder feature

2. Create `FacilityAssetServiceImpl`

3. Implement CRUD with code generation and transaction support

4. Implement warranty expiry check:
   - Find facilities where warranty_expire_date is within N days

## Technical Notes

- Reference: PRD section 6.3.2
- Same pattern as HouseAssetService
