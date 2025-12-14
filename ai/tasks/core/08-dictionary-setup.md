---
id: core.dictionary-setup
module: core
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [configuration, dictionary]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Configure System Dictionaries for Asset Management

## Context

All dropdown selections and status values are managed through RuoYi's dictionary system. This task sets up all required dictionaries for the asset management modules.

## Acceptance Criteria

1. Asset type dictionaries created:
   - `asset_type`: HOUSE, PARKING, FACILITY, VENUE, OFFICE
   - `asset_status`: All unified state machine values
   - `ownership_type`: OWNED, RENTED, MANAGED
   - `depreciation_method`: STRAIGHT_LINE, etc.
2. House-specific dictionaries:
   - `house_type`: 1BR, 2BR, 3BR, etc.
   - `house_usage`: RESIDENTIAL, COMMERCIAL, APARTMENT
   - `house_current_usage`: SELF_USE, RENTED, VACANT
3. Parking-specific dictionaries:
   - `parking_type`: FIXED, TEMPORARY
   - `parking_zone`: UNDERGROUND, GROUND, etc.
4. Facility-specific dictionaries:
   - `facility_type`: ELEVATOR, ACCESS_CONTROL, LIGHTING, etc.
5. Venue-specific dictionaries:
   - `venue_type`: BASKETBALL, BADMINTON, MEETING_ROOM, etc.
   - `venue_use_mode`: FREE, PAID, INTERNAL
   - `venue_status`: AVAILABLE, OCCUPIED, MAINTENANCE
6. Office-specific dictionaries:
   - `office_type`: FURNITURE, IT_EQUIPMENT, OTHER
7. Maintenance dictionaries:
   - `maint_order_status`: WAIT_ASSIGN, WAIT_ACCEPT, PROCESSING, WAIT_CONFIRM, CLOSED, CANCELED
   - `maint_priority`: NORMAL, IMPORTANT, URGENT
   - `maint_fault_type`: ELECTRICAL, PLUMBING, HVAC, etc.
8. SQL insert scripts created for all dictionaries

## Technical Notes

- Use sys_dict_type and sys_dict_data tables
- Dictionary codes should be consistent with enum values
- Frontend uses `DictTag` component for display
