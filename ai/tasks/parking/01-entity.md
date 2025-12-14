---
id: parking.entity
module: parking
priority: 29
status: passing
version: 24
origin: manual
dependsOn:
  - core.asset-base-entity
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:52:38.953Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7fe7ce55a2f247a07c4cb60794e340fc6f50d12b
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:50:31.147Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/parking/entity/AssetParkingTest.java
      - src/test/java/com/asset/parking/dto/AssetParkingDTOTest.java
      - src/test/java/com/asset/parking/vo/AssetParkingVOTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetParking entity with reference to Asset base
      assertions:
        - assertNotNull(assetParking.getAsset())
        - 'assertEquals(asset.getId(), assetParking.getAsset().getId())'
    - name: should map all extension fields from t_asset_parking table
      assertions:
        - assertNotNull(assetParking.getParkingNumber())
        - assertNotNull(assetParking.getParkingType())
        - assertNotNull(assetParking.getArea())
        - assertNotNull(assetParking.getFloor())
    - name: should create valid VO class with correct field mappings
      assertions:
        - assertNotNull(assetParkingVO.getId())
        - assertNotNull(assetParkingVO.getParkingNumber())
        - 'assertEquals(entity.getParkingNumber(), vo.getParkingNumber())'
    - name: should create valid DTO class with correct field mappings
      assertions:
        - assertNotNull(assetParkingDTO.getId())
        - assertNotNull(assetParkingDTO.getParkingNumber())
        - assertDoesNotThrow(() -> mapper.toEntity(dto))
    - name: should validate required fields with validation annotations
      assertions:
        - >-
          assertThrows(ConstraintViolationException.class, () ->
          validator.validate(invalidEntity))
        - >-
          assertTrue(violations.stream().anyMatch(v ->
          v.getPropertyPath().toString().equals("parkingNumber")))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Parking Asset Entity

## Context

Parking assets represent parking spaces with usage status and rental information.

## Acceptance Criteria

1. Create `AssetParking` entity referencing `Asset` base
2. Map extension fields from `t_asset_parking`:
3. Create VO and DTO classes
4. Add validation annotations
## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_parking)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetParking`
