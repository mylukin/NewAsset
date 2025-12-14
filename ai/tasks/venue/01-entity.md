---
id: venue.entity
module: venue
priority: 39
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
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:09:05.873Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 33327c77817a5c000eb155ec7fb548aafebf5c7f
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:06:50.638Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/venue/entity/AssetVenueTest.java
      - src/test/java/com/newasset/venue/dto/VenueDtoTest.java
      - src/test/java/com/newasset/venue/vo/VenueVoTest.java
    e2e: []
  unitTestCases:
    - name: AssetVenue entity should reference Asset base entity correctly
      assertions:
        - assertNotNull(assetVenue.getAsset())
        - 'assertEquals(assetId, assetVenue.getAsset().getId())'
        - 'assertInstanceOf(Asset.class, assetVenue.getAsset())'
    - name: AssetVenue should map all extension fields from t_asset_venue table
      assertions:
        - assertNotNull(assetVenue.getVenueType())
        - assertNotNull(assetVenue.getCapacity())
        - assertNotNull(assetVenue.getAddress())
        - >-
          assertEquals(expectedFieldCount,
          ReflectionUtils.getFieldCount(AssetVenue.class))
    - name: VenueVO should contain all required view object fields
      assertions:
        - assertNotNull(venueVo.getId())
        - assertNotNull(venueVo.getName())
        - assertDoesNotThrow(() -> new VenueVO())
    - name: VenueDTO should contain all required data transfer fields
      assertions:
        - assertNotNull(venueDto.getId())
        - assertNotNull(venueDto.getName())
        - assertDoesNotThrow(() -> new VenueDTO())
    - name: AssetVenue should have proper validation annotations
      assertions:
        - >-
          assertTrue(hasAnnotation(AssetVenue.class, 'venueType',
          NotNull.class))
        - 'assertTrue(hasAnnotation(AssetVenue.class, ''capacity'', Min.class))'
        - >-
          assertThrows(ConstraintViolationException.class, () ->
          validator.validate(invalidVenue))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Venue Asset Entity

## Context

Venue assets represent spaces like sports facilities, conference rooms, and activity centers with capacity and availability info.

## Acceptance Criteria

1. Create `AssetVenue` entity referencing `Asset` base
2. Map extension fields from `t_asset_venue`:
3. Create VO and DTO classes
4. Add validation annotations
## Technical Notes

- Reference: TECH.md Section 4.1.2 (t_asset_venue)
- Pattern: One-to-one extension table
- Location: `com.ruoyi.asset.domain.entity.AssetVenue`
