---
id: facility.crud
module: facility
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [facility.entity, core.code-generator, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Facility Asset CRUD Operations

## Context

Full CRUD operations for facility (residential supporting equipment) assets. Includes maintenance vendor information and warranty tracking.

## Acceptance Criteria

1. Backend APIs:
   - `GET /asset/facility/list` - paginated list
   - `GET /asset/facility/{id}` - detail
   - `POST /asset/facility` - create
   - `PUT /asset/facility` - update
   - `DELETE /asset/facility/{ids}` - soft delete
   - `POST /asset/facility/export` - Excel export
2. List filters:
   - projectId, building, floor
   - facilityType, status
   - maintOrg (maintenance vendor)
   - warranty status (active/expired)
3. Create/Edit form:
   - Project, building, floor, location description
   - Facility type, equipment number
   - Installation position details
   - Maintenance vendor, contact phone
   - Common asset fields
4. Frontend page `views/asset/facility/index.vue`:
   - Facility-specific filters
   - Warranty expiry column with badge
   - Quick access to maintenance vendor info
5. Detail view:
   - Maintenance history emphasized
   - Vendor contact prominent

## Technical Notes

- Warranty status calculated from warrantyExpireDate
- Facility types vary widely (dictionary)
- Consider QR code for facility identification (future)
