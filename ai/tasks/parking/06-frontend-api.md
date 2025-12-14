---
id: parking.frontend-api
module: parking
priority: 34
status: passing
version: 23
origin: manual
dependsOn:
  - parking.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:00:37.650Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: b1e885eae09b7593506fbf215c2a674b5ece0786
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:00:04.344Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/api/parking/ParkingApiTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateParkingApiFileInCorrectLocation
      assertions:
        - assertTrue(Files.exists(Paths.get("src/api/asset/parking.js")))
        - assertNotNull(fileContent)
    - name: shouldImplementGetParkingListFunction
      assertions:
        - assertThat(apiModule).containsFunction("getParkingList")
        - verify(request).get("/asset/parking/list")
    - name: shouldImplementGetParkingByIdFunction
      assertions:
        - assertThat(apiModule).containsFunction("getParkingById")
        - 'verify(request).get("/asset/parking/{id}")'
    - name: shouldImplementCreateParkingFunction
      assertions:
        - assertThat(apiModule).containsFunction("createParking")
        - verify(request).post("/asset/parking")
    - name: shouldImplementUpdateParkingFunction
      assertions:
        - assertThat(apiModule).containsFunction("updateParking")
        - verify(request).put("/asset/parking")
    - name: shouldImplementDeleteParkingFunction
      assertions:
        - assertThat(apiModule).containsFunction("deleteParking")
        - 'verify(request).delete("/asset/parking/{id}")'
    - name: shouldUseRuoYiRequestUtility
      assertions:
        - >-
          assertThat(fileContent).contains("import request from
          '@/utils/request'")
        - assertThat(apiCalls).allMatch(call -> call.usesRequestUtility())
    - name: shouldExportAllApiFunctions
      assertions:
        - >-
          assertThat(exports).containsExactlyInAnyOrder("getParkingList",
          "getParkingById", "createParking", "updateParking", "deleteParking")
        - assertThat(fileContent).contains("export")
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Parking Asset Frontend API Module

## Context

Axios API module for parking asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/parking.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/parking.js`
