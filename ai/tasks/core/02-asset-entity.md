---
id: core.asset-entity
module: core
priority: 102
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - core.database-schema
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Create Asset Base Entity and Mapper

## Context

Java entity class and MyBatis mapper for the base asset table. Forms the foundation for all asset type modules.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`:
   - All fields from t_asset mapped with correct types
   - Lombok annotations (@Data, @Builder, etc.) or standard getters/setters
   - Validation annotations on required fields (@NotBlank, @NotNull)
   - @TableName annotation for MyBatis-Plus or XML mapping

2. Create `AssetMapper` interface in `com.ruoyi.asset.mapper`:
   - Extends BaseMapper<Asset> (if using MyBatis-Plus)
   - Or standard MyBatis mapper with XML
   - Basic CRUD methods

3. Create mapper XML file (if not using MyBatis-Plus):
   - Location: `resources/mapper/asset/AssetMapper.xml`
   - ResultMap for Asset entity
   - Basic select, insert, update, delete statements

4. Create `AssetVO` and `AssetDTO` classes in `com.ruoyi.asset.domain.vo`:
   - AssetVO for list/detail responses
   - AssetCreateDTO for create requests
   - AssetUpdateDTO for update requests

5. Entity compiles without errors and mapper XML is valid

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Tech Stack: MyBatis 3.5.x)
- Follow RuoYi entity conventions
- Use BaseEntity from RuoYi for audit fields if available
