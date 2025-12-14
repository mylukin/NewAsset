---
id: venue.entity
module: venue
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T05:00:48.318Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/venue/domain/AssetVenueTest.java
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetVenue for t_asset_venue with capacity and
        availability fields
      assertions:
        - 'assertEquals("ASSET-001", assetVenue.getAssetId())'
        - 'assertEquals("basketball", assetVenue.getVenueType())'
        - 'assertEquals(Integer.valueOf(30), assetVenue.getCapacity())'
        - 'assertEquals("free", assetVenue.getUseMode())'
        - 'assertEquals("Mon-Fri 09:00-18:00", assetVenue.getOpenTimeDesc())'
        - 'assertEquals("available", assetVenue.getCurrentVenueStatus())'
    - name: >-
        should return AssetVenueVO that combines base asset fields with venue
        extension and current availability
      assertions:
        - 'assertEquals("ASSET-001", vo.getAssetId())'
        - 'assertEquals("Community Basketball Court", vo.getAssetName())'
        - 'assertEquals(30, vo.getCapacity())'
        - 'assertEquals("available", vo.getCurrentVenueStatus())'
    - name: should accept AssetVenueCreateDTO and map it to an AssetVenue entity
      assertions:
        - 'assertEquals("meeting_room", dto.getVenueType())'
        - 'assertEquals(Integer.valueOf(12), dto.getCapacity())'
        - 'assertEquals("paid", dto.getUseMode())'
        - 'assertEquals("Sat-Sun 10:00-22:00", dto.getOpenTimeDesc())'
        - 'assertEquals("occupied", dto.getCurrentVenueStatus())'
    - name: >-
        should accept AssetVenueUpdateDTO and apply updates to capacity and
        currentVenueStatus
      assertions:
        - 'assertEquals("ASSET-001", updateDto.getAssetId())'
        - 'assertEquals(Integer.valueOf(50), updateDto.getCapacity())'
        - 'assertEquals("maintenance", updateDto.getCurrentVenueStatus())'
        - 'assertEquals(Integer.valueOf(50), updatedEntity.getCapacity())'
        - 'assertEquals("maintenance", updatedEntity.getCurrentVenueStatus())'
    - name: should define AssetVenueMapper interface with standard CRUD methods
      assertions:
        - >-
          assertNotNull(AssetVenueMapper.class.getMethod("insertAssetVenue",
          AssetVenue.class))
        - >-
          assertNotNull(AssetVenueMapper.class.getMethod("updateAssetVenue",
          AssetVenue.class))
        - >-
          assertNotNull(AssetVenueMapper.class.getMethod("selectAssetVenueByAssetId",
          String.class))
        - >-
          assertNotNull(AssetVenueMapper.class.getMethod("deleteAssetVenueByAssetId",
          String.class))
  e2eScenarios: []
  frameworkHint: junit-jupiter
---
# Create venue asset entity with capacity and availability info
