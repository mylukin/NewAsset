---
id: facility.controller
module: facility
priority: 23
status: passing
version: 24
origin: manual
dependsOn:
  - facility.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:40:16.586Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 4e5d0e99e648248418ddc526adf3c9404bd8edf5
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:39:44.893Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetFacilityControllerTest.java
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetFacilityController extending BaseController with
        correct annotations
      assertions:
        - assertNotNull(controller)
        - assertTrue(controller instanceof BaseController)
        - >-
          assertNotNull(AssetFacilityController.class.getAnnotation(RestController.class))
        - >-
          assertEquals("/asset/facility",
          AssetFacilityController.class.getAnnotation(RequestMapping.class).value()[0])
    - name: should implement GET /list endpoint with pagination and permission
      assertions:
        - >-
          mockMvc.perform(get("/asset/facility/list")).andExpect(status().isOk())
        - assertNotNull(method.getAnnotation(PreAuthorize.class))
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:list"))
    - name: 'should implement GET /{id} endpoint with query permission'
      assertions:
        - mockMvc.perform(get("/asset/facility/1")).andExpect(status().isOk())
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:query"))
    - name: should implement POST endpoint with add permission and @Log annotation
      assertions:
        - mockMvc.perform(post("/asset/facility")).andExpect(status().isOk())
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:add"))
        - >-
          assertEquals(BusinessType.INSERT,
          method.getAnnotation(Log.class).businessType())
    - name: should implement PUT endpoint with edit permission and @Log annotation
      assertions:
        - mockMvc.perform(put("/asset/facility")).andExpect(status().isOk())
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:edit"))
        - >-
          assertEquals(BusinessType.UPDATE,
          method.getAnnotation(Log.class).businessType())
    - name: >-
        should implement DELETE /{ids} endpoint with remove permission and @Log
        annotation
      assertions:
        - >-
          mockMvc.perform(delete("/asset/facility/1,2,3")).andExpect(status().isOk())
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:remove"))
        - >-
          assertEquals(BusinessType.DELETE,
          method.getAnnotation(Log.class).businessType())
    - name: >-
        should implement POST /export endpoint with export permission and @Log
        annotation
      assertions:
        - >-
          mockMvc.perform(post("/asset/facility/export")).andExpect(status().isOk())
        - >-
          assertTrue(method.getAnnotation(PreAuthorize.class).value().contains("asset:facility:export"))
        - >-
          assertEquals(BusinessType.EXPORT,
          method.getAnnotation(Log.class).businessType())
    - name: should return standard RuoYi AjaxResult response format
      assertions:
        - >-
          assertTrue(method.getReturnType().equals(AjaxResult.class) ||
          method.getReturnType().equals(TableDataInfo.class))
  e2eScenarios: []
  frameworkHint: junit5-mockito-spring
---
# Create Facility Asset Controller

## Context

RESTful API controller for facility asset management.

## Acceptance Criteria

1. Create `AssetFacilityController`
2. Implement endpoints:
3. Add permission annotations: `asset:facility:*`
4. Add operation logging with @Log
5. Return standard RuoYi response format
## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:facility:list, query, add, edit, remove, export
