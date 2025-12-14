---
id: house.entity
module: house
priority: 10
status: passing
version: 22
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
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:10:41.140Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 05839612656ca11147f87e2e1900855beb602493
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:08:56.903Z'
  generatedBy: claude
  forVersion: 13
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/asset/house/entity/AssetHouseTest.java
      - src/test/java/com/newasset/asset/house/dto/AssetHouseDtoTest.java
      - src/test/java/com/newasset/asset/house/vo/AssetHouseVoTest.java
    e2e: []
  unitTestCases:
    - name: AssetHouse entity should extend or reference Asset base correctly
      assertions:
        - assertNotNull(assetHouse.getAsset())
        - 'assertInstanceOf(Asset.class, assetHouse.getAsset())'
    - name: AssetHouse entity should map all extension fields from t_asset_house
      assertions:
        - 'assertEquals(expectedBuildingArea, assetHouse.getBuildingArea())'
        - 'assertEquals(expectedFloorCount, assetHouse.getFloorCount())'
        - 'assertEquals(expectedPropertyCertNo, assetHouse.getPropertyCertNo())'
        - assertNotNull(assetHouse.getHouseType())
    - name: AssetHouseListVO should contain required list view fields
      assertions:
        - assertNotNull(listVo.getId())
        - assertNotNull(listVo.getAssetCode())
        - assertNotNull(listVo.getAssetName())
        - assertNotNull(listVo.getBuildingArea())
    - name: AssetHouseDetailVO should contain all detail view fields
      assertions:
        - assertNotNull(detailVo.getId())
        - assertNotNull(detailVo.getAssetCode())
        - assertNotNull(detailVo.getPropertyCertNo())
        - assertNotNull(detailVo.getAddress())
    - name: >-
        AssetHouseCreateDTO should have validation annotations for required
        fields
      assertions:
        - >-
          assertTrue(hasNotNullAnnotation(AssetHouseCreateDTO.class,
          "assetName"))
        - >-
          assertTrue(hasNotNullAnnotation(AssetHouseCreateDTO.class,
          "buildingArea"))
    - name: >-
        AssetHouseUpdateDTO should have validation annotations for required
        fields
      assertions:
        - 'assertTrue(hasNotNullAnnotation(AssetHouseUpdateDTO.class, "id"))'
        - >-
          assertTrue(hasNotBlankAnnotation(AssetHouseUpdateDTO.class,
          "assetName"))
  e2eScenarios: []
  frameworkHint: junit5
---
# Create House Asset Entity

## Context

House assets extend the base asset with property-specific fields like area, house type, and rental information.

## Acceptance Criteria

1. Create `AssetHouse` entity extending or referencing `Asset` base
2. Map all extension fields from `t_asset_house`:
3. Create VO classes for list and detail views
4. Create DTO classes for create and update operations
5. Add validation annotations for required fields
## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_house)
- Pattern: One-to-one extension table relationship
- Location: `com.ruoyi.asset.domain.entity.AssetHouse`
