---
id: facility.entity
module: facility
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
# Create Facility Asset Entity and Extension

## Context

Facility assets represent residential supporting equipment like elevators, access control systems, lighting, and fitness equipment. They have maintenance vendor information.

## Acceptance Criteria

1. `AssetFacility` entity created for `t_asset_facility`:
   - assetId (PK, FK to t_asset)
   - facilityType (elevator, access_control, lighting, etc.)
   - installPosition (detailed installation location)
   - equipmentNo (manufacturer's serial number)
   - maintOrg (maintenance vendor name)
   - maintPhone (maintenance contact)
2. `AssetFacilityVO` for list/detail responses:
   - Combines base asset fields + facility extension
   - Includes warranty status (expired/active)
3. `AssetFacilityCreateDTO` for creation
4. `AssetFacilityUpdateDTO` for updates
5. `AssetFacilityMapper` interface with standard CRUD

## Technical Notes

- facilityType from dictionary
- Warranty expiry date from base asset
- Support equipment hierarchy (future: parent-child)
