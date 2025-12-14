---
id: facility.controller
module: facility
priority: 23
status: failing
version: 1
origin: manual
dependsOn:
  - facility.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: "tests/facility/**/*.test.*"
---
# Create Facility Asset Controller

## Context

RESTful API controller for facility asset management.

## Acceptance Criteria

1. Create `AssetFacilityController`
2. Implement endpoints:
   - `GET /asset/facility/list` - paginated list
   - `GET /asset/facility/{id}` - get detail
   - `POST /asset/facility` - create
   - `PUT /asset/facility` - update
   - `DELETE /asset/facility/{ids}` - delete
   - `POST /asset/facility/export` - export Excel
3. Add permission annotations: `asset:facility:*`
4. Add operation logging with @Log
5. Return standard RuoYi response format

## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:facility:list, query, add, edit, remove, export
