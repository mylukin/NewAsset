---
id: venue.crud
module: venue
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [venue.entity, core.code-generator, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Venue Asset CRUD Operations

## Context

Full CRUD operations for venue (activity space) assets. Focuses on venue type, capacity, usage mode, and availability status.

## Acceptance Criteria

1. Backend APIs:
   - `GET /asset/venue/list` - paginated list
   - `GET /asset/venue/{id}` - detail
   - `POST /asset/venue` - create
   - `PUT /asset/venue` - update
   - `DELETE /asset/venue/{ids}` - soft delete
   - `POST /asset/venue/export` - Excel export
2. List filters:
   - projectId, venueType
   - useMode, status
   - currentVenueStatus (availability)
3. Create/Edit form:
   - Project, location
   - Venue name, type
   - Capacity
   - Usage mode (free/paid/internal)
   - Open time description
   - Current availability status
   - Common asset fields
4. Frontend page `views/asset/venue/index.vue`:
   - Venue-specific filters
   - Availability status badge
   - Capacity display
5. Detail view:
   - Tabs for info, maintenance, attachments
   - Initiate maintenance button

## Technical Notes

- Venue types vary (sports, meeting, activity)
- currentVenueStatus manually managed for MVP
- Consider calendar view for usage (future)
