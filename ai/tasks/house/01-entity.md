---
id: house.entity
module: house
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [domain, entity]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset Entity and Extension

## Context

House assets represent residential properties with specific attributes like area, room type, and rental information. This extends the base asset entity.

## Acceptance Criteria

1. `AssetHouse` entity created for `t_asset_house`:
   - assetId (PK, FK to t_asset)
   - buildingArea, innerArea (decimal)
   - houseType (1BR, 2BR, etc.)
   - houseUsage (residential, commercial)
   - currentUsage (self-use, rented, vacant)
   - currentUser, contractNo
   - rentUnitPrice, rentTotal
2. `AssetHouseVO` for list/detail responses:
   - Combines base asset fields + house extension
   - Includes project name, department name
3. `AssetHouseCreateDTO` for creation:
   - All required base asset fields
   - House-specific fields
   - Validation annotations
4. `AssetHouseUpdateDTO` for updates
5. `AssetHouseMapper` interface with:
   - selectHouseList(query) - paginated list
   - selectHouseById(id) - detail with base asset
   - insertHouse(entity)
   - updateHouse(entity)
   - deleteHouseByIds(ids) - soft delete

## Technical Notes

- One-to-one relationship with t_asset
- Use MyBatis resultMap for joined queries
- Reference: TECH.md section 4.1.2
