---
id: facility.frontend-api
module: facility
priority: 24
status: passing
version: 24
origin: manual
dependsOn:
  - facility.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:41:01.445Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: f8a0a0533e4b66e71bd54453584b5ba18829acd6
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:40:38.933Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/facility/frontend-api.test.js
    e2e: []
  unitTestCases:
    - name: should create facility.js file at /src/api/asset/facility.js
      assertions:
        - expect(fs.existsSync('/src/api/asset/facility.js')).toBe(true)
    - name: >-
        should implement listFacilityAssets function that calls request with
        correct params
      assertions:
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/facility/list',
          method: 'get', params: query })
        - expect(result).toBeDefined()
    - name: should implement getFacilityAsset function that fetches by id
      assertions:
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/facility/' + id,
          method: 'get' })
        - expect(result).toBeDefined()
    - name: should implement addFacilityAsset function that posts data
      assertions:
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/facility', method:
          'post', data: facilityData })
        - expect(result).toBeDefined()
    - name: should implement updateFacilityAsset function that puts data
      assertions:
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/facility', method:
          'put', data: facilityData })
        - expect(result).toBeDefined()
    - name: should implement deleteFacilityAsset function that deletes by ids
      assertions:
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/facility/' + ids,
          method: 'delete' })
        - expect(result).toBeDefined()
    - name: should use RuoYi request utility from @/utils/request
      assertions:
        - >-
          expect(importStatement).toContain("import request from
          '@/utils/request'")
    - name: should export all API functions
      assertions:
        - expect(exports.listFacilityAssets).toBeDefined()
        - expect(exports.getFacilityAsset).toBeDefined()
        - expect(exports.addFacilityAsset).toBeDefined()
        - expect(exports.updateFacilityAsset).toBeDefined()
        - expect(exports.deleteFacilityAsset).toBeDefined()
  e2eScenarios: []
  frameworkHint: jest
---
# Create Facility Asset Frontend API Module

## Context

Axios API module for facility asset frontend communication.

## Acceptance Criteria

1. Create `/src/api/asset/facility.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- Pattern: RuoYi API module
- File: `ruoyi-ui/src/api/asset/facility.js`
