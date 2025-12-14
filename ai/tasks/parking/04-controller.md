---
id: parking.controller
module: parking
priority: 8
status: failing
version: 1
origin: manual
dependsOn: [parking.service]
supersedes: []
tags: [backend, controller, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/parking/**/*.test.*"
---
# Create Parking Asset Controller

## Context

RESTful API endpoints for parking asset operations.

## Acceptance Criteria

1. Create `ParkingAssetController`:
   - Base path: `/asset/parking`

2. Implement endpoints:
   - `GET /asset/parking/list`
   - `GET /asset/parking/{id}`
   - `POST /asset/parking`
   - `PUT /asset/parking`
   - `DELETE /asset/parking/{ids}`
   - `POST /asset/parking/export`
   - `GET /asset/parking/statistics` - get utilization stats
   - `GET /asset/parking/statistics/by-zone` - grouped by zone

3. Add permission annotations:
   - `asset:parking:*`

4. Add `@Log` annotations

## Technical Notes

- Reference: TECH.md section 6.1
