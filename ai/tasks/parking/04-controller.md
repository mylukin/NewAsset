---
id: parking.controller
module: parking
priority: 32
status: failing
version: 3
origin: manual
dependsOn:
  - parking.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Controller

## Context

RESTful API controller for parking asset management.

## Acceptance Criteria

1. Create `AssetParkingController`
2. Implement endpoints:
   - GET /asset/parking/list, GET /asset/parking/{id}
   - POST /asset/parking, PUT /asset/parking
   - DELETE /asset/parking/{ids}, POST /asset/parking/export
3. Add permission annotations: `asset:parking:*`
4. Add operation logging
5. Return standard response format

## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:parking:list, query, add, edit, remove, export
