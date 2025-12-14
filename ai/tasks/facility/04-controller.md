---
id: facility.controller
module: facility
priority: 12
status: failing
version: 1
origin: manual
dependsOn: [facility.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/facility/**/*.test.*"
---
# Create Facility Asset Controller

## Context

The controller exposes RESTful API endpoints for facility asset operations.

## Acceptance Criteria

1. Create `FacilityAssetController`:
   - Base path: `/asset/facility`

2. Implement endpoints:
   - `GET /asset/facility/list` - list with filters
   - `GET /asset/facility/{id}` - get detail
   - `POST /asset/facility` - create
   - `PUT /asset/facility` - update
   - `DELETE /asset/facility/{ids}` - delete
   - `POST /asset/facility/export` - export Excel

3. Add permission annotations:
   - `asset:facility:list`
   - `asset:facility:query`
   - `asset:facility:add`
   - `asset:facility:edit`
   - `asset:facility:remove`
   - `asset:facility:export`

4. Add `@Log` annotations for audit

## Technical Notes

- Reference: TECH.md section 6.1
- Follow RuoYi controller conventions
