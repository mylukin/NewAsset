---
id: venue.controller
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn: [venue.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/venue/**/*.test.*"
---
# Create Venue Asset Controller

## Context

RESTful API endpoints for venue asset operations.

## Acceptance Criteria

1. Create `VenueAssetController`:
   - Base path: `/asset/venue`

2. Implement endpoints:
   - `GET /asset/venue/list`
   - `GET /asset/venue/{id}`
   - `POST /asset/venue`
   - `PUT /asset/venue`
   - `DELETE /asset/venue/{ids}`
   - `POST /asset/venue/export`
   - `PUT /asset/venue/{id}/status` - update venue status

3. Add permission annotations:
   - `asset:venue:*`

4. Add `@Log` annotations

## Technical Notes

- Reference: TECH.md section 6.1
