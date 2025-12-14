---
id: maintenance.maint-plan
module: maintenance
priority: 68
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.order-workflow-service
supersedes: []
tags:
  - service
  - scheduler
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:56:10.817Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 20682c5e1b6277ebc6c833c9ab346e2bbdfa9ae3
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:50:27.938Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - ruoyi-admin/src/test/java/com/ruoyi/asset/entity/AssetMaintPlanTest.java
      - >-
        ruoyi-admin/src/test/java/com/ruoyi/asset/entity/AssetMaintPlanAssetTest.java
      - >-
        ruoyi-admin/src/test/java/com/ruoyi/asset/mapper/AssetMaintPlanMapperTest.java
      - >-
        ruoyi-admin/src/test/java/com/ruoyi/asset/service/AssetMaintPlanServiceTest.java
      - >-
        ruoyi-admin/src/test/java/com/ruoyi/asset/controller/AssetMaintPlanControllerTest.java
      - ruoyi-admin/src/test/java/com/ruoyi/asset/job/MaintPlanJobTest.java
    e2e:
      - ruoyi-ui/e2e/maintenance/maint-plan.spec.ts
  unitTestCases:
    - name: AssetMaintPlan entity should map to t_asset_maint_plan table correctly
      assertions:
        - assertNotNull(plan.getPlanId())
        - 'assertEquals("t_asset_maint_plan", plan.getTableName())'
        - assertNotNull(plan.getPlanName())
        - assertNotNull(plan.getCycleType())
        - assertNotNull(plan.getCycleValue())
    - name: AssetMaintPlanAsset entity should establish plan-asset association
      assertions:
        - assertNotNull(planAsset.getPlanId())
        - assertNotNull(planAsset.getAssetId())
        - 'assertEquals(expectedPlanId, planAsset.getPlanId())'
        - 'assertEquals(expectedAssetId, planAsset.getAssetId())'
    - name: AssetMaintPlanMapper should perform CRUD operations
      assertions:
        - 'assertEquals(1, mapper.insertAssetMaintPlan(plan))'
        - assertNotNull(mapper.selectAssetMaintPlanById(planId))
        - 'assertEquals(1, mapper.updateAssetMaintPlan(plan))'
        - 'assertEquals(1, mapper.deleteAssetMaintPlanById(planId))'
        - assertTrue(mapper.selectAssetMaintPlanList(query).size() >= 0)
    - name: AssetMaintPlanService should handle plan CRUD with validation
      assertions:
        - 'assertEquals(1, service.insertAssetMaintPlan(plan))'
        - assertNotNull(service.selectAssetMaintPlanById(planId))
        - 'assertEquals(1, service.updateAssetMaintPlan(plan))'
        - 'assertEquals(1, service.deleteAssetMaintPlanByIds(ids))'
    - name: AssetMaintPlanController should expose REST endpoints
      assertions:
        - 'assertEquals(HttpStatus.OK, response.getStatusCode())'
        - assertNotNull(response.getBody().getData())
        - 'assertEquals("success", response.getBody().getMsg())'
    - name: >-
        MaintPlanJob should generate maintenance orders based on cycle
        configuration
      assertions:
        - 'verify(maintOrderService, times(1)).insertMaintOrder(any())'
        - 'assertEquals(expectedOrderCount, generatedOrders.size())'
        - assertNotNull(generatedOrder.getPlanId())
        - 'assertEquals(planId, generatedOrder.getPlanId())'
  e2eScenarios:
    - name: user can view maintenance plan list
      steps:
        - navigate to /maintenance/plan
        - verify table displays plan list
        - 'verify columns include plan name, cycle type, status'
        - verify pagination controls are visible
    - name: user can create a new maintenance plan
      steps:
        - navigate to /maintenance/plan
        - click add button
        - fill in plan name field
        - select cycle type (daily/weekly/monthly)
        - enter cycle value
        - select assets to include
        - click submit button
        - verify success message displays
        - verify new plan appears in list
    - name: user can edit an existing maintenance plan
      steps:
        - navigate to /maintenance/plan
        - click edit button on existing plan
        - modify plan name
        - modify cycle configuration
        - click submit button
        - verify success message displays
        - verify updated plan reflects changes
    - name: user can delete a maintenance plan
      steps:
        - navigate to /maintenance/plan
        - click delete button on existing plan
        - confirm deletion in dialog
        - verify success message displays
        - verify plan is removed from list
    - name: user can associate assets with a maintenance plan
      steps:
        - navigate to /maintenance/plan
        - click edit or view plan
        - navigate to asset association section
        - select assets from available list
        - save associations
        - verify associated assets display correctly
  frameworkHint: junit5-surefire
---
# Implement Maintenance Plan (P1)

## Context

Scheduled maintenance plans automatically generate work orders based on asset type, project, and cycle configuration.

## Acceptance Criteria

1. Create entity `AssetMaintPlan` mapping `t_asset_maint_plan`:
2. Create entity `AssetMaintPlanAsset` for plan-asset association
3. Create mapper and service for plan CRUD
4. Create controller endpoints:
5. Create scheduled job (using RuoYi Quartz):
6. Create frontend for plan management:
## Technical Notes

- Reference: PRD Section 6.1.2 Point 4, TECH.md Section 4.2.4
- Pattern: Scheduled job with Quartz
- Integration: Use MaintOrderWorkflowService.createOrder()
