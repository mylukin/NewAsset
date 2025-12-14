---
id: office.controller
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn: [office.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/office/**/*.test.*"
---
# Create Office Asset Controller

## Context

RESTful API endpoints for office asset operations.

## Acceptance Criteria

1. Create `OfficeAssetController`:
   - Base path: `/asset/office`

2. Implement endpoints:
   - `GET /asset/office/list`
   - `GET /asset/office/{id}`
   - `POST /asset/office`
   - `PUT /asset/office`
   - `DELETE /asset/office/{ids}`
   - `POST /asset/office/export`
   - `GET /asset/office/statistics/condition` - condition rate (P1)

3. Add permission annotations:
   - `asset:office:*`

4. Add `@Log` annotations

## Technical Notes

- Reference: TECH.md section 6.1
