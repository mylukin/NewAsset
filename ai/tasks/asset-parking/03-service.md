---
id: asset-parking.service
module: asset-parking
priority: 303
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.entity
  - core.base-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: true
    pattern: "tests/asset-parking/**/*.test.*"
    cases:
      - "should create parking asset with extension"
      - "should update parking fields"
---
# Implement Parking Asset Service

## Context

Service for parking asset CRUD operations. Extends base asset service with parking-specific fields.

## Acceptance Criteria

1. Create `IAssetParkingService` interface:
   - `AssetParkingVO create(AssetParkingCreateDTO dto)`
   - `AssetParkingVO update(AssetParkingUpdateDTO dto)`
   - `void delete(Long[] ids)`
   - `AssetParkingVO getById(Long id)`
   - `PageResult<AssetParkingVO> list(AssetParkingQueryDTO query)`

2. Create `AssetParkingServiceImpl`:
   - Inject AssetMapper, AssetParkingMapper, AssetCodeGenerator
   - Transactional operations

3. Create operation:
   - Generate asset code (PA-XXXXXX)
   - Insert into t_asset with asset_type='PARKING'
   - Insert into t_asset_parking

4. Update operation:
   - Update both tables in same transaction

5. Delete operation:
   - Call base service delete (checks for open work orders)

## Technical Notes

- Similar structure to AssetHouseService
- Reference: ai/tasks/asset-house/03-service.md
