---
id: office.controller
module: office
priority: 51
status: failing
version: 1
origin: manual
dependsOn:
  - office.service
supersedes: []
tags:
  - controller
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Controller

## Context

RESTful API controller for office asset management.

## Acceptance Criteria

1. Create `AssetOfficeController`
2. Implement endpoints:
   - GET /asset/office/list, GET /asset/office/{id}
   - POST /asset/office, PUT /asset/office
   - DELETE /asset/office/{ids}, POST /asset/office/export
3. Add permission annotations: `asset:office:*`
4. Add operation logging
5. Return standard response format

## Technical Notes

- Reference: TECH.md Section 6
- Pattern: RuoYi BaseController
- Permissions: asset:office:list, query, add, edit, remove, export
