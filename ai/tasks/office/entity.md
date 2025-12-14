---
id: office.entity
module: office
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T04:43:16.606Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - >-
        office/src/test/java/com/yourorg/office/entity/OfficeAssetEntityTest.java
    e2e: []
  unitTestCases:
    - name: should expose IT equipment fields on OfficeAsset entity
      assertions:
        - 'assertEquals("ASSET-001", officeAsset.getAssetTag())'
        - 'assertEquals("LAPTOP", officeAsset.getDeviceType())'
        - 'assertEquals("SN-123456", officeAsset.getSerialNumber())'
        - 'assertEquals("Dell", officeAsset.getManufacturer())'
        - 'assertEquals("XPS 13", officeAsset.getModel())'
        - 'assertEquals("Windows 11", officeAsset.getOperatingSystem())'
        - 'assertEquals(16, officeAsset.getRamGb())'
        - 'assertEquals(512, officeAsset.getStorageGb())'
  e2eScenarios: []
  frameworkHint: junit-jupiter
---
# Create office asset entity with IT equipment fields
