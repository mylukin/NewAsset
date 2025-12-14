---
id: maintenance.order-entity
module: maintenance
priority: 57
status: passing
version: 24
origin: manual
dependsOn:
  - core.database-schema
  - core.asset-status-enum
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:37:23.626Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7d0984269c8faa3eec743da425cb4efecca756a1
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:35:38.431Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/asset/maintenance/entity/AssetMaintOrderTest.java
      - >-
        src/test/java/com/example/asset/maintenance/vo/AssetMaintOrderVOTest.java
      - >-
        src/test/java/com/example/asset/maintenance/dto/AssetMaintOrderCreateDTOTest.java
    e2e: []
  unitTestCases:
    - name: should map AssetMaintOrder entity to t_asset_maint_order table
      assertions:
        - assertNotNull(entity.getClass().getAnnotation(Table.class))
        - >-
          assertEquals("t_asset_maint_order",
          entity.getClass().getAnnotation(Table.class).name())
    - name: should have all required fields mapped correctly
      assertions:
        - assertNotNull(entity.getId())
        - assertNotNull(entity.getOrderNo())
        - assertNotNull(entity.getAssetId())
        - assertNotNull(entity.getMaintType())
        - assertNotNull(entity.getStatus())
    - name: should create list VO with summary fields
      assertions:
        - assertNotNull(listVO.getId())
        - assertNotNull(listVO.getOrderNo())
        - assertNotNull(listVO.getAssetName())
        - assertNotNull(listVO.getStatus())
    - name: should create detail VO with all fields
      assertions:
        - assertNotNull(detailVO.getId())
        - assertNotNull(detailVO.getOrderNo())
        - assertNotNull(detailVO.getDescription())
        - assertNotNull(detailVO.getMaintItems())
    - name: should create DTO for create operations with validation
      assertions:
        - assertNotNull(createDTO.getAssetId())
        - assertNotNull(createDTO.getMaintType())
        - >-
          assertTrue(createDTO.getClass().getDeclaredField("assetId").isAnnotationPresent(NotNull.class))
    - name: should extend BaseEntity with audit fields
      assertions:
        - assertTrue(BaseEntity.class.isAssignableFrom(AssetMaintOrder.class))
        - assertNotNull(entity.getCreateTime())
        - assertNotNull(entity.getUpdateTime())
        - assertNotNull(entity.getCreateBy())
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Maintenance Order Entity

## Context

Maintenance orders track repair requests and work orders linked to assets with workflow status tracking.

## Acceptance Criteria

1. Create `AssetMaintOrder` entity mapping `t_asset_maint_order`
2. Map all fields:
3. Create VO for list and detail views
4. Create DTO for create operations
5. Extend BaseEntity for audit fields
## Technical Notes

- Reference: TECH.md Section 4.2.1
- Pattern: Entity with status workflow
- Location: `com.ruoyi.asset.domain.entity.AssetMaintOrder`
