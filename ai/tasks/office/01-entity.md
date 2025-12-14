---
id: office.entity
module: office
priority: 48
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
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:23:45.535Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 08a774a5c4f4d28f2c178602bfea7bcf1e852a64
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:21:28.049Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/asset/office/entity/AssetOfficeTest.java
      - src/test/java/com/example/asset/office/dto/AssetOfficeDTOTest.java
      - src/test/java/com/example/asset/office/vo/AssetOfficeVOTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetOffice entity with reference to Asset base
      assertions:
        - assertNotNull(assetOffice.getAsset())
        - 'assertEquals(asset.getId(), assetOffice.getAsset().getId())'
    - name: should map all extension fields from t_asset_office table
      assertions:
        - assertNotNull(assetOffice.getOfficeSpecificField())
        - 'assertEquals(expectedValue, assetOffice.getMappedField())'
    - name: should create valid VO class with proper field mapping
      assertions:
        - assertNotNull(assetOfficeVO)
        - 'assertEquals(entity.getId(), assetOfficeVO.getId())'
    - name: should create valid DTO class with proper field mapping
      assertions:
        - assertNotNull(assetOfficeDTO)
        - 'assertEquals(vo.getId(), assetOfficeDTO.getId())'
    - name: should validate required fields with validation annotations
      assertions:
        - >-
          assertThrows(ConstraintViolationException.class, () ->
          validator.validate(invalidEntity))
        - assertTrue(violations.isEmpty())
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Office Asset Entity

## Context

Office assets include furniture, IT equipment, and other office items with user assignment and condition tracking.

## Acceptance Criteria

1. Create `AssetOffice` entity referencing `Asset` base
2. Map extension fields from `t_asset_office`:
3. Create VO and DTO classes
4. Add validation annotations
## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_office)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetOffice`
