---
id: house.frontend-api
module: house
priority: 15
status: passing
version: 22
origin: manual
dependsOn:
  - house.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:23:39.746Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ed4997bde86ee28381434ef5b00ca8791aeca9e1
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:22:55.361Z'
  generatedBy: claude
  forVersion: 19
  suggestedTestFiles:
    unit:
      - src/test/java/com/asset/api/house/HouseApiTest.java
    e2e: []
  unitTestCases:
    - name: should create house.js file at correct path
      assertions:
        - assertThat(Files.exists(Paths.get("src/api/asset/house.js"))).isTrue()
    - name: should implement getHouseAssetList function
      assertions:
        - assertThat(houseApi).containsFunction("getHouseAssetList")
        - assertThat(getHouseAssetList(params)).returnsPromise()
    - name: should implement getHouseAsset function
      assertions:
        - assertThat(houseApi).containsFunction("getHouseAsset")
        - assertThat(getHouseAsset(id)).returnsPromise()
    - name: should implement addHouseAsset function
      assertions:
        - assertThat(houseApi).containsFunction("addHouseAsset")
        - assertThat(addHouseAsset(data)).callsRequest("post")
    - name: should implement updateHouseAsset function
      assertions:
        - assertThat(houseApi).containsFunction("updateHouseAsset")
        - assertThat(updateHouseAsset(data)).callsRequest("put")
    - name: should implement deleteHouseAsset function
      assertions:
        - assertThat(houseApi).containsFunction("deleteHouseAsset")
        - assertThat(deleteHouseAsset(id)).callsRequest("delete")
    - name: should use RuoYi request utility for HTTP calls
      assertions:
        - assertThat(houseApi).importsFrom("@/utils/request")
        - assertThat(houseApi).usesRequestUtility()
    - name: should export all API functions
      assertions:
        - >-
          assertThat(exports).contains("getHouseAssetList", "getHouseAsset",
          "addHouseAsset", "updateHouseAsset", "deleteHouseAsset")
  e2eScenarios: []
  frameworkHint: surefire
---
# Create House Asset Frontend API Module

## Context

Axios-based API module for frontend to communicate with house asset backend endpoints.

## Acceptance Criteria

1. Create `/src/api/asset/house.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- Pattern: RuoYi API module pattern
- File: `ruoyi-ui/src/api/asset/house.js`
