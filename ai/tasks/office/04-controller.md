---
id: office.controller
module: office
priority: 51
status: passing
version: 23
origin: manual
dependsOn:
  - office.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:27:30.362Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 746cedd669ef1244a7adb98a11f62b13ad7f7fe2
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:27:03.887Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/asset/controller/AssetOfficeControllerTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetOfficeController class with required dependencies
      assertions:
        - assertNotNull(controller)
        - 'assertInstanceOf(AssetOfficeController.class, controller)'
    - name: should implement CRUD endpoints for office assets
      assertions:
        - mockMvc.perform(get('/api/asset/office')).andExpect(status().isOk())
        - >-
          mockMvc.perform(post('/api/asset/office')).andExpect(status().isCreated())
        - >-
          mockMvc.perform(put('/api/asset/office/{id}')).andExpect(status().isOk())
        - >-
          mockMvc.perform(delete('/api/asset/office/{id}')).andExpect(status().isNoContent())
    - name: 'should require asset:office:* permissions on endpoints'
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertThat(annotation.value()).contains(''asset:office'')'
    - name: should log operations for audit trail
      assertions:
        - verify(operationLogService).log(any(OperationLog.class))
        - assertThat(logEntry.getModule()).isEqualTo('office')
    - name: 'should return standard response format with code, message, and data'
      assertions:
        - jsonPath('$.code').value(200)
        - jsonPath('$.message').exists()
        - jsonPath('$.data').exists()
  e2eScenarios: []
  frameworkHint: junit5-spring-mockMvc
---
# Create Office Asset Controller

## Context

RESTful API controller for office asset management.

## Acceptance Criteria

1. Create `AssetOfficeController`
2. Implement endpoints:
3. Add permission annotations: `asset:office:*`
4. Add operation logging
5. Return standard response format
## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:office:list, query, add, edit, remove, export
