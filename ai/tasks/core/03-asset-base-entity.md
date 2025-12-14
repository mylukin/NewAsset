---
id: core.asset-base-entity
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [core.database-schema]
supersedes: []
tags: [backend, entity]
testRequirements:
  unit:
    required: true
    pattern: "tests/core/**/*.test.*"
---
# Create Base Asset Entity

## Context

The `Asset` entity is the core domain object that represents all types of assets. It contains common fields shared across house, parking, facility, venue, and office assets.

## Acceptance Criteria

1. Create `Asset` entity class in `com.ruoyi.asset.domain.entity`
2. Map all fields from `t_asset` table:
   - Use `@TableName("t_asset")` for MyBatis-Plus or XML mapping
   - Include all common fields as per PRD section 4.3
3. Extend RuoYi's `BaseEntity` for audit fields
4. Add proper validation annotations (@NotBlank, @Size, etc.)
5. Create `AssetTypeEnum` enum for asset types (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
6. Create `OwnershipTypeEnum` for ownership types
7. Override `toString()` for logging purposes

## Technical Notes

- Reference: TECH.md section 4.1.1
- Pattern: JPA/MyBatis entity mapping with RuoYi conventions
