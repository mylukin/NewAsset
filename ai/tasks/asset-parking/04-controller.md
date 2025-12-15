---
id: asset-parking.controller
module: asset-parking
priority: 304
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.service
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-parking/**/*.test.*"
---
# Implement Parking Asset REST API

## Context

REST API for parking asset CRUD operations.

## Acceptance Criteria

1. Create `AssetParkingController` in `com.ruoyi.asset.controller`:
   - Base path: `/asset/parking`

2. Implement endpoints:

   **GET /asset/parking/list**
   - Query: projectId, parkingZone, parkingNo, status, parkingType, pageNum, pageSize
   - Permission: asset:parking:list

   **GET /asset/parking/{id}**
   - Permission: asset:parking:query

   **POST /asset/parking**
   - Permission: asset:parking:add

   **PUT /asset/parking**
   - Permission: asset:parking:edit

   **DELETE /asset/parking/{ids}**
   - Permission: asset:parking:remove

   **POST /asset/parking/export**
   - Permission: asset:parking:export

3. Add @Log annotation for audit logging

4. Apply @DataScope for project-based permission

## Technical Notes

- Similar structure to AssetHouseController
- Reference: ai/tasks/asset-house/04-controller.md
