---
id: parking.entity
module: parking
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
# Create Parking Asset Entity and Extension

## Context

Parking assets represent parking spaces with specific attributes like zone, type (fixed/temporary), and rental information.

## Acceptance Criteria

1. `AssetParking` entity created for `t_asset_parking`:
   - assetId (PK, FK to t_asset)
   - parkingNo (parking space number)
   - parkingZone (underground, ground, etc.)
   - parkingType (fixed, temporary)
   - area (decimal, optional)
   - currentUser, plateNo
   - rentPrice, contractNo
2. `AssetParkingVO` for list/detail responses:
   - Combines base asset fields + parking extension
   - Includes project name, zone name
3. `AssetParkingCreateDTO` for creation:
   - Required: projectId, parkingZone, parkingNo
   - Validation annotations
4. `AssetParkingUpdateDTO` for updates
5. `AssetParkingMapper` interface with:
   - selectParkingList(query)
   - selectParkingById(id)
   - insertParking(entity)
   - updateParking(entity)
   - deleteParkingByIds(ids)

## Technical Notes

- parkingNo should be unique within project+zone
- Support batch creation for parking lots
- Reference: TECH.md section 4.1.2
