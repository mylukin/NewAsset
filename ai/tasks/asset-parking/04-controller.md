---
id: asset-parking.controller
module: asset-parking
priority: 304
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-parking.service
supersedes: []
tags:
  - backend
  - api
testRequirements:
  unit:
    required: false
    pattern: tests/asset-parking/**/*.test.*
verification:
  verifiedAt: '2025-12-15T14:20:49.544Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 5d2350d2a7a5fd1a2cd698f7cf972f196076c37f
  summary: 4/4 criteria satisfied
---
# Implement Parking Asset REST API

## Context

REST API for parking asset CRUD operations.

## Acceptance Criteria

1. Create `AssetParkingController` in `com.ruoyi.asset.controller`:
2. Implement endpoints:
3. Add @Log annotation for audit logging
4. Apply @DataScope for project-based permission
## Technical Notes

- Similar structure to AssetHouseController
- Reference: ai/tasks/asset-house/04-controller.md
