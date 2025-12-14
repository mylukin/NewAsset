---
id: office.frontend-api
module: office
priority: 52
status: passing
version: 23
origin: manual
dependsOn:
  - office.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:28:12.424Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 5e7ffdba1a56d77bafe10e3680c96f8891b86219
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:27:51.310Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/api/asset/office.test.js
    e2e: []
  unitTestCases:
    - name: should create office.js file at /src/api/asset/office.js path
      assertions:
        - expect(fs.existsSync('/src/api/asset/office.js')).toBe(true)
    - name: >-
        should implement listOfficeAsset function that calls GET
        /asset/office/list
      assertions:
        - expect(listOfficeAsset).toBeDefined()
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/office/list',
          method: 'get', params: expect.any(Object) })
    - name: >-
        should implement getOfficeAsset function that calls GET
        /asset/office/{id}
      assertions:
        - expect(getOfficeAsset).toBeDefined()
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/office/1', method:
          'get' })
    - name: should implement addOfficeAsset function that calls POST /asset/office
      assertions:
        - expect(addOfficeAsset).toBeDefined()
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/office', method:
          'post', data: expect.any(Object) })
    - name: should implement updateOfficeAsset function that calls PUT /asset/office
      assertions:
        - expect(updateOfficeAsset).toBeDefined()
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/office', method:
          'put', data: expect.any(Object) })
    - name: >-
        should implement delOfficeAsset function that calls DELETE
        /asset/office/{id}
      assertions:
        - expect(delOfficeAsset).toBeDefined()
        - >-
          expect(request).toHaveBeenCalledWith({ url: '/asset/office/1', method:
          'delete' })
    - name: should use RuoYi request utility from @/utils/request
      assertions:
        - expect(importStatement).toContain('import request from')
        - expect(importStatement).toContain('@/utils/request')
    - name: should export all API functions
      assertions:
        - expect(exports.listOfficeAsset).toBeDefined()
        - expect(exports.getOfficeAsset).toBeDefined()
        - expect(exports.addOfficeAsset).toBeDefined()
        - expect(exports.updateOfficeAsset).toBeDefined()
        - expect(exports.delOfficeAsset).toBeDefined()
  e2eScenarios: []
  frameworkHint: jest
---
# Create Office Asset Frontend API Module

## Context

Axios API module for office asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/office.js`
2. Implement API functions:
3. Use RuoYi request utility
4. Export all functions
## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/office.js`
