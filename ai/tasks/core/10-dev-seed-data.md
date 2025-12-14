---
id: core.dev-seed-data
module: core
priority: 10
status: passing
version: 23
origin: manual
dependsOn:
  - core.database-schema
  - core.dictionary-setup
supersedes: []
tags:
  - infrastructure
  - database
  - development
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:13:32.504Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 6a81ea2964556e199620f7494364dc6dc9292da3
  summary: 14/14 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:11:08.709Z'
  generatedBy: claude
  forVersion: 14
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/seed/DevSeedDataTest.java
    e2e: []
  unitTestCases:
    - name: seedDataDevSqlFileExists
      assertions:
        - assertTrue(Files.exists(Paths.get("sql/seed-data-dev.sql")))
        - assertNotNull(fileContent)
    - name: seedDataMysqlFileExistsIfNeeded
      assertions:
        - >-
          assertTrue(Files.exists(Paths.get("sql/seed-data-mysql.sql")) ||
          mysqlSyntaxNotRequired())
    - name: seedDataIsIdempotent
      assertions:
        - assertDoesNotThrow(() -> executeSeedScript())
        - assertDoesNotThrow(() -> executeSeedScript())
        - 'assertEquals(expectedCount, actualCount)'
    - name: sampleProjectsCreatedWithDifferentCharacteristics
      assertions:
        - assertTrue(projectCount >= 2 && projectCount <= 3)
        - >-
          assertNotEquals(project1.getCharacteristics(),
          project2.getCharacteristics())
    - name: sampleHouseAssetsCreatedPerProject
      assertions:
        - assertTrue(houseAssetCount >= 3 && houseAssetCount <= 5)
        - assertNotNull(houseAsset.getProjectId())
    - name: sampleParkingAssetsCreatedPerProject
      assertions:
        - assertTrue(parkingAssetCount >= 5 && parkingAssetCount <= 10)
        - assertNotNull(parkingAsset.getProjectId())
    - name: sampleFacilityAssetsCreatedPerProject
      assertions:
        - assertTrue(facilityAssetCount >= 3 && facilityAssetCount <= 5)
        - assertNotNull(facilityAsset.getProjectId())
    - name: sampleVenueAssetsCreatedPerProject
      assertions:
        - assertTrue(venueAssetCount >= 2 && venueAssetCount <= 3)
        - assertNotNull(venueAsset.getProjectId())
    - name: sampleOfficeAssetsCreatedPerProject
      assertions:
        - assertTrue(officeAssetCount >= 5 && officeAssetCount <= 10)
        - assertNotNull(officeAsset.getProjectId())
    - name: sampleMaintenanceOrdersInVariousStatuses
      assertions:
        - >-
          assertTrue(maintenanceOrders.stream().map(o ->
          o.getStatus()).distinct().count() >= 3)
        - assertFalse(maintenanceOrders.isEmpty())
    - name: sampleMaintenanceLogsForCompletedOrders
      assertions:
        - >-
          assertTrue(completedOrders.stream().allMatch(o ->
          !o.getMaintenanceLogs().isEmpty()))
        - assertNotNull(maintenanceLog.getCompletionDate())
    - name: defaultAssetCodeGenerationRulesForEachAssetType
      assertions:
        - assertNotNull(codeRuleForHouse)
        - assertNotNull(codeRuleForParking)
        - assertNotNull(codeRuleForFacility)
        - assertNotNull(codeRuleForVenue)
        - assertNotNull(codeRuleForOffice)
    - name: sequenceCountersInitializedForSampleProjects
      assertions:
        - assertTrue(sequenceCounter.getCurrentValue() >= 0)
        - assertNotNull(sequenceCounter.getProjectId())
    - name: dictionaryDataIncludedOrReferenced
      assertions:
        - assertFalse(dictionaryEntries.isEmpty())
        - >-
          assertTrue(seedScript.contains("sys_dict") ||
          dictionaryDataPreloaded())
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Development Seed Data

## Context

Development environment requires sample data for immediate testing and development. This includes sample projects, assets of each type, and related reference data to enable developers to work without manual data entry.

## Acceptance Criteria

1. Create `sql/seed-data-dev.sql` with sample data for development
2. Create `sql/seed-data-mysql.sql` (if MySQL syntax differs significantly)
3. Seed data should be idempotent (safe to run multiple times)
4. Create 2-3 sample projects with different characteristics:
5. Create sample house assets (3-5 per project):
6. Create sample parking assets (5-10 per project):
7. Create sample facility assets (3-5 per project):
8. Create sample venue assets (2-3 per project):
9. Create sample office assets (5-10 per project):
10. Create sample maintenance orders in various statuses:
11. Create sample maintenance logs for completed orders
12. Create default asset code generation rules for each asset type
13. Initialize sequence counters for sample projects
14. Ensure dictionary data is included or referenced:
## Technical Notes

- Use INSERT statements with explicit column names
- Include DELETE statements at the top for idempotency (or use INSERT OR REPLACE for SQLite)
- Match foreign key references to RuoYi system tables (sys_dept, sys_user)
- Use realistic Chinese names and descriptions for better UX testing
- Date values should be relative or use recent dates

## Sample Data Volume

| Entity | Count | Notes |
|--------|-------|-------|
| Projects | 3 | Different property types |
| Houses | 10 | Across projects |
| Parking | 20 | Various types |
| Facilities | 15 | Include warranty scenarios |
| Venues | 8 | Different capacities |
| Offices | 25 | Mix of equipment types |
| Maintenance Orders | 10 | Various statuses |
| Attachments | 5 | Sample file references |
