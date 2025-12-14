---
id: facility.entity
module: facility
priority: 20
status: passing
version: 23
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
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:34:56.537Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 17e8145d0ca1ee01ea31e3b89511fa03963c5b48
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:32:27.208Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/facility/entity/AssetFacilityEntityTest.java
      - src/test/java/com/newasset/facility/vo/AssetFacilityVOTest.java
      - src/test/java/com/newasset/facility/dto/AssetFacilityDTOTest.java
    e2e: []
  unitTestCases:
    - name: AssetFacility entity should reference Asset base entity correctly
      assertions:
        - assertNotNull(assetFacility.getAsset())
        - 'assertEquals(assetId, assetFacility.getAsset().getId())'
    - name: >-
        AssetFacility entity should map all extension fields from
        t_asset_facility
      assertions:
        - assertNotNull(assetFacility.getFacilityType())
        - assertNotNull(assetFacility.getLocation())
        - assertNotNull(assetFacility.getCapacity())
    - name: AssetFacilityListVO should contain summary fields for list view
      assertions:
        - assertNotNull(listVO.getId())
        - assertNotNull(listVO.getName())
        - assertNotNull(listVO.getFacilityType())
    - name: AssetFacilityDetailVO should contain all fields for detail view
      assertions:
        - assertNotNull(detailVO.getId())
        - assertNotNull(detailVO.getAssetInfo())
        - assertNotNull(detailVO.getFacilityType())
        - assertNotNull(detailVO.getLocation())
    - name: AssetFacilityCreateDTO should have required fields for create operation
      assertions:
        - assertNotNull(createDTO.getAssetId())
        - assertNotNull(createDTO.getFacilityType())
    - name: AssetFacilityUpdateDTO should have required fields for update operation
      assertions:
        - assertNotNull(updateDTO.getId())
        - assertNotNull(updateDTO.getFacilityType())
    - name: Validation annotations should reject null required fields
      assertions:
        - >-
          assertThrows(ConstraintViolationException.class, () ->
          validator.validate(invalidDTO))
        - assertTrue(violations.size() > 0)
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Facility Asset Entity

## Context

Facility assets represent residential supporting equipment like elevators, access control, and public lighting systems.

## Acceptance Criteria

1. Create `AssetFacility` entity referencing `Asset` base
2. Map extension fields from `t_asset_facility`:
3. Create VO classes for list and detail views
4. Create DTO classes for create and update operations
5. Add validation annotations for required fields
## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_facility)
- Pattern: One-to-one extension table relationship
- Location: `com.ruoyi.asset.domain.entity.AssetFacility`
