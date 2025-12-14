---
id: house.entity
module: house
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
verification:
  verifiedAt: '2025-12-14T04:38:42.267Z'
  verdict: fail
  verifiedBy: codex
  commitHash: c159456fe0e5848b3f133bb091d2668a31ae79b8
  summary: 0/0 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T04:36:05.704Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - src/test/java/house/entity/HouseAssetEntityTest.java
    e2e: []
  unitTestCases:
    - name: should create a House asset entity in the house module
      assertions:
        - assertNotNull(new HouseAssetEntity())
        - 'assertEquals("house", HouseAssetEntity.MODULE)'
    - name: should expose extension field for area
      assertions:
        - HouseAssetEntity entity = new HouseAssetEntity()
        - 'entity.setExtensionField("area", 120.5)'
        - 'assertEquals(120.5, (Double) entity.getExtensionField("area"))'
    - name: should expose extension field for type and rental info
      assertions:
        - HouseAssetEntity entity = new HouseAssetEntity()
        - 'entity.setExtensionField("type", "apartment")'
        - >-
          entity.setExtensionField("rental", Map.of("isForRent", true,
          "monthlyRent", 3500))
        - 'assertEquals("apartment", (String) entity.getExtensionField("type"))'
        - >-
          assertTrue(((Map<?, ?>)
          entity.getExtensionField("rental")).containsKey("monthlyRent"))
        - >-
          assertEquals(3500, ((Map<?, ?>)
          entity.getExtensionField("rental")).get("monthlyRent"))
  e2eScenarios: []
  frameworkHint: junit5 (maven-surefire)
---
# Create house asset entity with extension fields for area, type, rental info
