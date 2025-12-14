---
id: house.service
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [house.mapper, core.code-generator, core.state-machine]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/house/**/*.test.*"
---
# Create House Asset Service

## Context

The service layer handles business logic for house assets including CRUD operations, code generation, and validation.

## Acceptance Criteria

1. Create `IHouseAssetService` interface:
   - `List<HouseAssetVO> selectHouseAssetList(HouseAssetQueryDTO query)`
   - `HouseAssetVO selectHouseAssetById(Long assetId)`
   - `Long insertHouseAsset(HouseAssetCreateDTO dto)`
   - `int updateHouseAsset(HouseAssetUpdateDTO dto)`
   - `int deleteHouseAssetByIds(Long[] assetIds)`
   - `HouseAssetVO copyHouseAsset(Long sourceAssetId)` - for same-floor copy

2. Create `HouseAssetServiceImpl`:

3. Implement `insertHouseAsset`:
   - Validate input DTO
   - Call `AssetCodeGenerator.generate()` for asset code
   - Insert into both `t_asset` and `t_asset_house` in transaction
   - Return new asset ID

4. Implement `updateHouseAsset`:
   - Validate asset exists and user has permission
   - Update both base and extension tables
   - Record changes in audit log for key fields

5. Implement `deleteHouseAssetByIds`:
   - Soft delete (set del_flag = '2')
   - Check for active maintenance orders before delete

6. Implement `copyHouseAsset`:
   - Create copy with new asset code
   - Useful for same-floor multi-unit scenarios

7. Use `@DataScope` for project-based access control

## Technical Notes

- Reference: TECH.md section 6.4
- Use `@Transactional` for multi-table operations
