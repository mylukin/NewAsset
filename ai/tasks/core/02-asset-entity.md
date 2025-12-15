---
id: core.asset-entity
module: core
priority: 102
status: failing
version: 7
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-15T15:07:53.338Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: ac57aedffb2da0cbe7b08a8b74d3afe05e68fe77
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T23:15:19.331Z'
  generatedBy: claude
  forVersion: 6
  suggestedTestFiles:
    unit:
      - ruoyi-asset/src/test/java/com/ruoyi/asset/domain/entity/AssetTest.java
      - ruoyi-asset/src/test/java/com/ruoyi/asset/mapper/AssetMapperTest.java
    e2e: []
  unitTestCases:
    - name: Asset entity class exists with required fields and annotations
      assertions:
        - expect Asset class to have @TableName annotation
        - >-
          expect Asset class to have id, assetCode, assetName, categoryId,
          status fields
        - expect Asset class to extend BaseEntity or have audit fields
    - name: AssetMapper interface exists with CRUD methods
      assertions:
        - expect AssetMapper to extend BaseMapper<Asset>
        - expect AssetMapper to have @Mapper annotation
        - expect selectById to return Asset entity
        - expect insert to persist Asset entity
    - name: Mapper XML file is valid and maps to entity
      assertions:
        - expect mapper XML namespace to match AssetMapper interface
        - expect resultMap to map all Asset entity fields
        - expect SQL statements to be syntactically valid
    - name: AssetVO class exists with view object fields
      assertions:
        - expect AssetVO class to have serializable annotation
        - expect AssetVO to contain display-friendly fields
        - expect AssetVO to have getter/setter methods
    - name: AssetDTO class exists for data transfer
      assertions:
        - expect AssetDTO class to have validation annotations
        - expect AssetDTO to contain input fields for create/update operations
        - expect AssetDTO to have getter/setter methods
    - name: Entity compiles without errors and mapper XML is valid
      assertions:
        - expect compilation to succeed without errors
        - expect mapper XML to parse without validation errors
        - expect Spring context to load AssetMapper bean successfully
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Asset Base Entity and Mapper

## Context

Java entity class and MyBatis mapper for the base asset table. Forms the foundation for all asset type modules.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`:
2. Create `AssetMapper` interface in `com.ruoyi.asset.mapper`:
3. Create mapper XML file (if not using MyBatis-Plus):
4. Create `AssetVO` and `AssetDTO` classes in `com.ruoyi.asset.domain.vo`:
5. Entity compiles without errors and mapper XML is valid
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Tech Stack: MyBatis 3.5.x)
- Follow RuoYi entity conventions
- Use BaseEntity from RuoYi for audit fields if available
