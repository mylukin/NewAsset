---
id: venue.controller
module: venue
priority: 42
status: failing
version: 12
origin: manual
dependsOn:
  - venue.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Create Venue Asset Controller

## Context

RESTful API controller for venue asset management.

## Acceptance Criteria

1. Create `AssetVenueController`
2. Implement endpoints:
   - GET /asset/venue/list, GET /asset/venue/{id}
   - POST /asset/venue, PUT /asset/venue
   - DELETE /asset/venue/{ids}, POST /asset/venue/export
3. Add permission annotations: `asset:venue:*`
4. Add operation logging
5. Return standard response format

## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:venue:list, query, add, edit, remove, export
