---
id: parking.controller
module: parking
priority: 32
status: passing
version: 24
origin: manual
dependsOn:
  - parking.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:56:32.995Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 720f722a59caa6c88065493a2c1e5b790a1743f6
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:55:54.331Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/newasset/controller/AssetParkingControllerTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateAssetParkingControllerClass
      assertions:
        - assertNotNull(controller)
        - assertTrue(controller instanceof AssetParkingController)
    - name: shouldHaveCreateEndpoint
      assertions:
        - mockMvc.perform(post('/api/parking')).andExpect(status().isOk())
        - verify(service).create(any())
    - name: shouldHaveUpdateEndpoint
      assertions:
        - 'mockMvc.perform(put(''/api/parking/{id}'')).andExpect(status().isOk())'
        - verify(service).update(any())
    - name: shouldHaveDeleteEndpoint
      assertions:
        - >-
          mockMvc.perform(delete('/api/parking/{id}')).andExpect(status().isOk())
        - verify(service).delete(anyLong())
    - name: shouldHaveGetByIdEndpoint
      assertions:
        - 'mockMvc.perform(get(''/api/parking/{id}'')).andExpect(status().isOk())'
        - verify(service).getById(anyLong())
    - name: shouldHaveListEndpoint
      assertions:
        - mockMvc.perform(get('/api/parking')).andExpect(status().isOk())
        - verify(service).list(any())
    - name: shouldRequireAssetParkingPermissions
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertTrue(annotation.value().contains(''asset:parking''))'
    - name: shouldLogOperations
      assertions:
        - assertTrue(method.isAnnotationPresent(OperationLog.class))
        - verify(operationLogService).log(any())
    - name: shouldReturnStandardResponseFormat
      assertions:
        - >-
          mockMvc.perform(get('/api/parking/{id}')).andExpect(jsonPath('$.code').exists())
        - >-
          mockMvc.perform(get('/api/parking/{id}')).andExpect(jsonPath('$.data').exists())
        - >-
          mockMvc.perform(get('/api/parking/{id}')).andExpect(jsonPath('$.message').exists())
  e2eScenarios: []
  frameworkHint: junit5-spring-boot-test
---
# Create Parking Asset Controller

## Context

RESTful API controller for parking asset management.

## Acceptance Criteria

1. Create `AssetParkingController`
2. Implement endpoints:
3. Add permission annotations: `asset:parking:*`
4. Add operation logging
5. Return standard response format
## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:parking:list, query, add, edit, remove, export
