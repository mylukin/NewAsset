---
id: maintenance.order-controller
module: maintenance
priority: 62
status: passing
version: 23
origin: manual
dependsOn:
  - maintenance.order-crud-service
  - maintenance.order-workflow-service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:44:21.229Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: e5943a26ee625945672bf20ef7dcd3d8e14ccf8c
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:43:36.645Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/newasset/maintenance/controller/AssetMaintOrderControllerTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetMaintOrderController class with proper annotations
      assertions:
        - assertNotNull(controller)
        - >-
          assertTrue(controller.getClass().isAnnotationPresent(RestController.class))
    - name: should implement CRUD endpoints - create maintenance order
      assertions:
        - mockMvc.perform(post).andExpect(status().isOk())
        - verify(service).create(any())
    - name: should implement CRUD endpoints - read maintenance order by id
      assertions:
        - mockMvc.perform(get).andExpect(status().isOk())
        - verify(service).getById(anyLong())
    - name: should implement CRUD endpoints - update maintenance order
      assertions:
        - mockMvc.perform(put).andExpect(status().isOk())
        - verify(service).update(any())
    - name: should implement CRUD endpoints - delete maintenance order
      assertions:
        - mockMvc.perform(delete).andExpect(status().isOk())
        - verify(service).delete(anyLong())
    - name: should implement workflow endpoints - submit order
      assertions:
        - mockMvc.perform(post('/submit')).andExpect(status().isOk())
        - verify(workflowService).submit(anyLong())
    - name: should implement workflow endpoints - approve order
      assertions:
        - mockMvc.perform(post('/approve')).andExpect(status().isOk())
        - verify(workflowService).approve(anyLong())
    - name: should implement workflow endpoints - reject order
      assertions:
        - mockMvc.perform(post('/reject')).andExpect(status().isOk())
        - 'verify(workflowService).reject(anyLong(), anyString())'
    - name: should implement workflow endpoints - complete order
      assertions:
        - mockMvc.perform(post('/complete')).andExpect(status().isOk())
        - verify(workflowService).complete(anyLong())
    - name: should implement query endpoints - list with pagination
      assertions:
        - mockMvc.perform(get('/page')).andExpect(status().isOk())
        - verify(service).page(any())
    - name: should implement query endpoints - search by criteria
      assertions:
        - mockMvc.perform(get('/list')).andExpect(status().isOk())
        - verify(service).list(any())
    - name: 'should have permission annotations for asset:maint:create'
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertEquals("hasAuthority(''asset:maint:create'')", annotation.value())'
    - name: 'should have permission annotations for asset:maint:update'
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertEquals("hasAuthority(''asset:maint:update'')", annotation.value())'
    - name: 'should have permission annotations for asset:maint:delete'
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertEquals("hasAuthority(''asset:maint:delete'')", annotation.value())'
    - name: 'should have permission annotations for asset:maint:query'
      assertions:
        - assertTrue(method.isAnnotationPresent(PreAuthorize.class))
        - 'assertEquals("hasAuthority(''asset:maint:query'')", annotation.value())'
    - name: should add operation logging annotation on controller methods
      assertions:
        - assertTrue(method.isAnnotationPresent(Log.class))
        - assertNotNull(annotation.title())
  e2eScenarios: []
  frameworkHint: junit5-spring-mockmvc
---
# Create Maintenance Order Controller

## Context

RESTful API controller for maintenance order management and workflow actions.

## Acceptance Criteria

1. Create `AssetMaintOrderController`
2. Implement CRUD endpoints:
3. Implement workflow endpoints:
4. Implement query endpoints:
5. Add permission annotations: `asset:maint:*`
6. Add operation logging
## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:maint:list, add, assign, accept, confirm, cancel
