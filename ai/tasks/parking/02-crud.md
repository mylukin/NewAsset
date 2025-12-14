---
id: parking.crud
module: parking
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [parking.entity, core.code-generator, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Parking Asset CRUD Operations

## Context

Full CRUD operations for parking assets including list, create, update, delete, and detail view. Similar pattern to house module but with parking-specific fields.

## Acceptance Criteria

1. Backend APIs:
   - `GET /asset/parking/list` - paginated list with filters
   - `GET /asset/parking/{id}` - detail view
   - `POST /asset/parking` - create new
   - `PUT /asset/parking` - update existing
   - `DELETE /asset/parking/{ids}` - soft delete
   - `POST /asset/parking/export` - Excel export
2. List filters:
   - projectId, parkingZone, parkingNo
   - parkingType, status, currentUser
   - plateNo search
3. Create/Edit form fields:
   - Project, zone, parking number
   - Parking type, area
   - Current user, plate number
   - Rent price, contract number
   - Common asset fields (ownership, value, etc.)
4. Frontend page `views/asset/parking/index.vue`:
   - Search form with parking-specific filters
   - Data table with relevant columns
   - Detail drawer with tabs
   - Maintenance initiation button
5. Excel export with parking columns

## Technical Notes

- Follow house module pattern
- Zone is dictionary-based
- Plate number format validation (optional)
