---
id: facility.entity
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn: [core.asset-base-entity]
supersedes: []
tags: [backend, entity, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/facility/**/*.test.*"
---
# Create Facility Asset Entity

## Context

Facility assets represent residential supporting equipment like elevators, access control systems, and public lighting.

## Acceptance Criteria

1. Create `FacilityAsset` entity in `com.ruoyi.asset.domain.entity`:
   - Map to `t_asset_facility` table (asset_id as PK/FK)

2. Include facility-specific fields:
   - `facilityType` (配套类型) - String (dict: facility_type)
   - `installPosition` (安装位置描述) - String
   - `equipmentNo` (设备编号/原厂家编号) - String
   - `maintOrg` (维保单位) - String
   - `maintPhone` (维保电话) - String

3. Create `FacilityAssetVO` for API responses

4. Create `FacilityAssetCreateDTO` and `FacilityAssetUpdateDTO`

## Technical Notes

- Reference: TECH.md section 4.1.2 (t_asset_facility)
- Pattern: One-to-one relationship with t_asset
