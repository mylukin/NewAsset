---
id: venue.controller
module: venue
priority: 42
status: passing
version: 24
origin: manual
dependsOn:
  - venue.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:13:11.215Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 43d71da668c0c2e76d05f1fc5d41b167bba4a8a1
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:12:44.891Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/controller/AssetVenueControllerTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateAssetVenueController
      assertions:
        - assertNotNull(controller)
        - 'assertInstanceOf(AssetVenueController.class, controller)'
    - name: shouldImplementCrudEndpoints
      assertions:
        - mockMvc.perform(get('/api/asset/venue')).andExpect(status().isOk())
        - >-
          mockMvc.perform(post('/api/asset/venue')).andExpect(status().isCreated())
        - >-
          mockMvc.perform(put('/api/asset/venue/{id}')).andExpect(status().isOk())
        - >-
          mockMvc.perform(delete('/api/asset/venue/{id}')).andExpect(status().isNoContent())
    - name: shouldRequirePermissionAnnotations
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertEquals("hasAuthority(''asset:venue:read'')", annotation.value())'
    - name: shouldLogOperations
      assertions:
        - verify(operationLogger).log(any(OperationLog.class))
        - assertNotNull(capturedLog.getTimestamp())
    - name: shouldReturnStandardResponseFormat
      assertions:
        - jsonPath('$.code').exists()
        - jsonPath('$.message').exists()
        - jsonPath('$.data').exists()
  e2eScenarios: []
  frameworkHint: junit5-mockito
---
# Create Venue Asset Controller

## Context

RESTful API controller for venue asset management.

## Acceptance Criteria

1. Create `AssetVenueController`
2. Implement endpoints:
3. Add permission annotations: `asset:venue:*`
4. Add operation logging
5. Return standard response format
## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:venue:list, query, add, edit, remove, export
