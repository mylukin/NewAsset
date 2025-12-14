---
id: house.create
module: house
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [house.entity, core.code-generator]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset Creation

## Context

Creating a house asset involves filling common asset fields, house-specific fields, and auto-generating a unique asset code based on the configured rules.

## Acceptance Criteria

1. Backend API `POST /asset/house`:
   - Request body: AssetHouseCreateDTO
   - Validation: required fields, value ranges
   - Permission: @PreAuthorize("asset:house:add")
2. Backend service `AssetHouseService.insertHouse()`:
   - Validate project exists and user has access
   - Call AssetCodeGenerator.generate() for asset code
   - Insert into t_asset and t_asset_house in transaction
   - Record creation in operation log
   - Return created asset with code
3. Frontend dialog/drawer:
   - Form sections:
     - Basic Info: project, building, floor, room, name
     - Location: detailed address description
     - House Details: areas, house type, usage
     - Ownership: type, owner, responsible department/person
     - Financial: purchase date, original value, depreciation
     - Supplier: brand, model, supplier, warranty
   - Asset code displayed after save (auto-generated)
   - Copy from existing house feature (same floor scenario)
4. Form validation:
   - Project required
   - Building/Floor/Room required
   - Area must be positive number
5. Error handling:
   - Code generation failure shows clear message
   - Duplicate code triggers retry

## Technical Notes

- Transaction spans code generation + inserts
- Use el-form with rules for validation
- Consider auto-save draft feature (future)
