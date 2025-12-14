---
id: house.controller
module: house
priority: 13
status: passing
version: 23
origin: manual
dependsOn:
  - house.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:19:20.187Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 5780a9ab30302c986631d88511e4be762c783505
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:18:32.438Z'
  generatedBy: claude
  forVersion: 17
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/controller/AssetHouseControllerTest.java
    e2e: []
  unitTestCases:
    - name: should create AssetHouseController in correct package
      assertions:
        - assertNotNull(controller)
        - >-
          assertTrue(controller.getClass().getPackage().getName().equals("com.ruoyi.asset.controller"))
    - name: should expose list endpoint with pagination
      assertions:
        - mockMvc.perform(get("/asset/house/list")).andExpect(status().isOk())
        - andExpect(jsonPath("$.rows").isArray())
        - andExpect(jsonPath("$.total").isNumber())
    - name: should expose export endpoint
      assertions:
        - >-
          mockMvc.perform(post("/asset/house/export")).andExpect(status().isOk())
    - name: should expose getInfo endpoint by id
      assertions:
        - >-
          mockMvc.perform(get("/asset/house/{id}",
          1L)).andExpect(status().isOk())
        - andExpect(jsonPath("$.data").exists())
    - name: should expose add endpoint
      assertions:
        - >-
          mockMvc.perform(post("/asset/house").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        - andExpect(jsonPath("$.code").value(200))
    - name: should expose edit endpoint
      assertions:
        - >-
          mockMvc.perform(put("/asset/house").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    - name: should expose remove endpoint
      assertions:
        - >-
          mockMvc.perform(delete("/asset/house/{ids}",
          "1,2,3")).andExpect(status().isOk())
    - name: should have permission annotation on list method
      assertions:
        - assertTrue(listMethod.isAnnotationPresent(PreAuthorize.class))
        - >-
          assertEquals("@ss.hasPermi('asset:house:list')",
          listMethod.getAnnotation(PreAuthorize.class).value())
    - name: should have Log annotation on insert method
      assertions:
        - assertTrue(addMethod.isAnnotationPresent(Log.class))
        - 'assertEquals("房源资产", addMethod.getAnnotation(Log.class).title())'
        - >-
          assertEquals(BusinessType.INSERT,
          addMethod.getAnnotation(Log.class).businessType())
    - name: should return AjaxResult for single operations
      assertions:
        - 'assertEquals(AjaxResult.class, addMethod.getReturnType())'
        - 'assertEquals(AjaxResult.class, editMethod.getReturnType())'
        - 'assertEquals(AjaxResult.class, removeMethod.getReturnType())'
    - name: should return TableDataInfo for list operation
      assertions:
        - 'assertEquals(TableDataInfo.class, listMethod.getReturnType())'
    - name: should have Swagger Api annotation on controller
      assertions:
        - assertTrue(AssetHouseController.class.isAnnotationPresent(Api.class))
        - >-
          assertNotNull(AssetHouseController.class.getAnnotation(Api.class).tags())
    - name: should have ApiOperation annotation on endpoints
      assertions:
        - assertTrue(listMethod.isAnnotationPresent(ApiOperation.class))
        - assertTrue(addMethod.isAnnotationPresent(ApiOperation.class))
  e2eScenarios: []
  frameworkHint: junit5-spring-mockmvc
---
# Create House Asset Controller

## Context

RESTful API controller for house asset management, following RuoYi conventions.

## Acceptance Criteria

1. Create `AssetHouseController` in `com.ruoyi.asset.controller`
2. Implement endpoints:
3. Add permission annotations: `@PreAuthorize("@ss.hasPermi('asset:house:list')")`
4. Add operation logging: `@Log(title = "房源资产", businessType = BusinessType.INSERT)`
5. Return standard RuoYi response format (AjaxResult, TableDataInfo)
6. Add Swagger/OpenAPI documentation annotations
## Technical Notes

- Reference: TECH.md Section 6.4
- Pattern: RuoYi BaseController extension
- Permissions: asset:house:list, asset:house:query, asset:house:add, asset:house:edit, asset:house:remove, asset:house:export
