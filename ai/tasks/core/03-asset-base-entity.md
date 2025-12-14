---
id: core.asset-base-entity
module: core
priority: 3
status: passing
version: 21
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - entity
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T11:57:13.938Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 747884f6e341d9281d2124df27dfff5f8380ced9
  summary: 8/8 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:56:24.564Z'
  generatedBy: claude
  forVersion: 5
  suggestedTestFiles:
    unit:
      - ruoyi-asset/src/test/java/com/ruoyi/asset/domain/entity/AssetTest.java
    e2e: []
  unitTestCases:
    - name: should create Asset entity in correct package
      assertions:
        - assertNotNull(new Asset())
        - >-
          assertEquals("com.ruoyi.asset.domain.entity",
          Asset.class.getPackage().getName())
    - name: should have all common fields mapped
      assertions:
        - assertNotNull(asset.getId())
        - assertNotNull(asset.getAssetCode())
        - assertNotNull(asset.getAssetName())
        - assertNotNull(asset.getAssetType())
        - assertNotNull(asset.getProjectId())
        - assertNotNull(asset.getBuilding())
        - assertNotNull(asset.getFloor())
        - assertNotNull(asset.getRoomNo())
        - assertNotNull(asset.getLocationDesc())
    - name: should have all ownership fields mapped
      assertions:
        - assertNotNull(asset.getOwnershipType())
        - assertNotNull(asset.getOwnerOrg())
        - assertNotNull(asset.getUseDeptId())
        - assertNotNull(asset.getDutyUserId())
    - name: should have all value fields mapped
      assertions:
        - assertNotNull(asset.getPurchaseDate())
        - assertNotNull(asset.getStartUseDate())
        - assertNotNull(asset.getOriginalValue())
        - assertNotNull(asset.getDepreciationMethod())
        - assertNotNull(asset.getDepreciationAmount())
        - assertNotNull(asset.getNetValue())
    - name: should have all supplier fields mapped
      assertions:
        - assertNotNull(asset.getBrand())
        - assertNotNull(asset.getModel())
        - assertNotNull(asset.getSupplier())
        - assertNotNull(asset.getWarrantyExpireDate())
    - name: should have status field with enum type
      assertions:
        - assertTrue(asset.getStatus() instanceof AssetStatus)
        - assertNotNull(AssetStatus.valueOf("NORMAL"))
    - name: should extend BaseEntity for audit fields
      assertions:
        - >-
          assertTrue(Asset.class.getSuperclass().getSimpleName().equals("BaseEntity"))
        - assertNotNull(asset.getCreateTime())
        - assertNotNull(asset.getUpdateTime())
    - name: should have validation annotations on required fields
      assertions:
        - >-
          assertTrue(Asset.class.getDeclaredField("assetCode").isAnnotationPresent(NotBlank.class))
        - >-
          assertTrue(Asset.class.getDeclaredField("assetName").isAnnotationPresent(NotBlank.class))
        - >-
          assertTrue(Asset.class.getDeclaredField("assetName").isAnnotationPresent(Size.class))
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Base Asset Entity and Common Fields

## Context

All asset types share common fields defined in `t_asset`. A base entity class provides reusable field definitions and mapping for all asset types.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`
2. Map all common fields: id, assetCode, assetName, assetType, projectId, building, floor, roomNo, locationDesc
3. Map ownership fields: ownershipType, ownerOrg, useDeptId, dutyUserId
4. Map value fields: purchaseDate, startUseDate, originalValue, depreciationMethod, depreciationAmount, netValue
5. Map supplier fields: brand, model, supplier, warrantyExpireDate
6. Include status field with proper enum mapping
7. Extend RuoYi `BaseEntity` for audit fields
8. Add proper validation annotations (@NotBlank, @Size, etc.)
## Technical Notes

- Reference: TECH.md Section 4.1.1
- Pattern: JPA/MyBatis entity with Lombok
- File: `com.ruoyi.asset.domain.entity.Asset`
