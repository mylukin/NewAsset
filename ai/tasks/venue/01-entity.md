---
id: venue.entity
module: venue
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
# Create Venue Asset Entity and Extension

## Context

Venue assets represent activity spaces like sports courts, meeting rooms, and activity centers. They have capacity and availability information.

## Acceptance Criteria

1. `AssetVenue` entity created for `t_asset_venue`:
   - assetId (PK, FK to t_asset)
   - venueType (basketball, badminton, meeting_room, etc.)
   - capacity (maximum people)
   - useMode (free, paid, internal)
   - openTimeDesc (text description of hours)
   - currentVenueStatus (available, occupied, maintenance)
2. `AssetVenueVO` for list/detail responses:
   - Combines base asset fields + venue extension
   - Current availability status
3. `AssetVenueCreateDTO` for creation
4. `AssetVenueUpdateDTO` for updates
5. `AssetVenueMapper` interface with standard CRUD

## Technical Notes

- venueType from dictionary
- currentVenueStatus is sub-status (different from asset status)
- openTimeDesc is free-form text for MVP
- Future: integrate with booking system
