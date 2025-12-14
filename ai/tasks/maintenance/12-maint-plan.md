---
id: maintenance.maint-plan
module: maintenance
priority: 68
status: failing
version: 17
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
---
# Implement Maintenance Plan (P1)

## Context

Scheduled maintenance plans automatically generate work orders based on asset type, project, and cycle configuration.

## Acceptance Criteria

1. Create entity `AssetMaintPlan` mapping `t_asset_maint_plan`:
   - planName, projectId, assetType/assetCategory
   - cycleType (每月/季度/年)
   - nextGenerateTime
   - status (启用/停用)
2. Create entity `AssetMaintPlanAsset` for plan-asset association
3. Create mapper and service for plan CRUD
4. Create controller endpoints:
   - CRUD for plans: /asset/maint/plan/*
   - Associate assets: POST /asset/maint/plan/{id}/assets
5. Create scheduled job (using RuoYi Quartz):
   - Check plans with nextGenerateTime <= now
   - Generate maintenance orders for associated assets
   - Update nextGenerateTime based on cycleType
6. Create frontend for plan management:
   - Plan list, create/edit form
   - Asset association interface
   - View generated orders

## Technical Notes

- Reference: PRD Section 6.1.2 Point 4, TECH.md Section 4.2.4
- Pattern: Scheduled job with Quartz
- Integration: Use MaintOrderWorkflowService.createOrder()
