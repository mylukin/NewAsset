---
id: facility.warranty-reminder
module: facility
priority: 28
status: passing
version: 24
origin: manual
dependsOn:
  - facility.service
supersedes: []
tags:
  - service
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:50:08.936Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 55bdfe4f64ac6d8af13dc5c8759d3fe8b898cefb
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:47:40.001Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetFacilityMapperTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/service/AssetFacilityServiceTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetFacilityControllerTest.java
    e2e: []
  unitTestCases:
    - name: >-
        selectExpiringFacilities should return facilities with warranty expiring
        within specified days
      assertions:
        - assertNotNull(result)
        - assertFalse(result.isEmpty())
        - >-
          assertTrue(result.stream().allMatch(f -> f.getWarrantyExpireDate() !=
          null))
    - name: >-
        getExpiringFacilities service method should delegate to mapper with
        correct daysAhead parameter
      assertions:
        - verify(assetFacilityMapper).selectExpiringFacilities(30)
        - 'assertEquals(expectedList, result)'
    - name: >-
        GET /asset/facility/expiring endpoint should return 200 with list of
        expiring facilities
      assertions:
        - andExpect(status().isOk())
        - andExpect(jsonPath('$.code').value(200))
        - andExpect(jsonPath('$.data').isArray())
    - name: >-
        expiring facilities response should include warranty expiry date and
        days remaining
      assertions:
        - 'andExpect(jsonPath(''$.data[0].warrantyExpireDate'').exists())'
        - 'andExpect(jsonPath(''$.data[0].daysRemaining'').isNumber())'
    - name: selectExpiringFacilities should filter by projectId when provided
      assertions:
        - 'assertEquals(1, result.size())'
        - 'assertEquals(projectId, result.get(0).getProjectId())'
    - name: >-
        selectExpiringFacilities should return empty list when no facilities are
        expiring
      assertions:
        - assertNotNull(result)
        - assertTrue(result.isEmpty())
  e2eScenarios: []
  frameworkHint: junit5-mockito
---
# Implement Warranty Expiry Reminder for Facilities (P1)

## Context

Facility assets with approaching warranty expiry dates should be listed for proactive maintenance planning.

## Acceptance Criteria

1. Add mapper method: `selectExpiringFacilities(int daysAhead)`
2. Add service method: `getExpiringFacilities(int daysAhead)`
3. Add controller endpoint: `GET /asset/facility/expiring`
4. Return list of facilities with warranty expiring within N days
5. Include expiry date and days remaining in response
6. Support project filter
## Technical Notes

- Reference: PRD Section 6.3.2 Point 4
- Query: WHERE warranty_expire_date BETWEEN NOW() AND NOW() + INTERVAL N DAY
- Default: 30 days ahead
