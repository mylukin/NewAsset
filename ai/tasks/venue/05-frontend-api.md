---
id: venue.frontend-api
module: venue
priority: 43
status: passing
version: 23
origin: manual
dependsOn:
  - venue.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:13:44.527Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 20f6bad3a005796ac7cb129692e9fed4623566f1
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:13:26.448Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/api/asset/VenueApiTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateVenueApiModuleAtCorrectPath
      assertions:
        - assertNotNull(venueApi)
        - assertTrue(Files.exists(Paths.get("src/api/asset/venue.js")))
    - name: shouldImplementListVenuesFunction
      assertions:
        - assertNotNull(venueApi.listVenues)
        - 'assertEquals(expectedResponse, venueApi.listVenues(params))'
    - name: shouldImplementGetVenueByIdFunction
      assertions:
        - assertNotNull(venueApi.getVenue)
        - 'assertEquals(expectedVenue, venueApi.getVenue(venueId))'
    - name: shouldImplementCreateVenueFunction
      assertions:
        - assertNotNull(venueApi.addVenue)
        - 'assertEquals(201, venueApi.addVenue(venueData).getStatus())'
    - name: shouldImplementUpdateVenueFunction
      assertions:
        - assertNotNull(venueApi.updateVenue)
        - 'assertEquals(200, venueApi.updateVenue(venueData).getStatus())'
    - name: shouldImplementDeleteVenueFunction
      assertions:
        - assertNotNull(venueApi.delVenue)
        - 'assertEquals(200, venueApi.delVenue(venueId).getStatus())'
    - name: shouldUseRuoYiRequestUtility
      assertions:
        - >-
          assertTrue(venueApiSource.contains("import request from
          '@/utils/request'"))
    - name: shouldExportAllApiFunctions
      assertions:
        - assertNotNull(venueApi.listVenues)
        - assertNotNull(venueApi.getVenue)
        - assertNotNull(venueApi.addVenue)
        - assertNotNull(venueApi.updateVenue)
        - assertNotNull(venueApi.delVenue)
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Venue Asset Frontend API Module

## Context

Axios API module for venue asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/venue.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/venue.js`
