---
id: asset-facility.warranty-alert
module: asset-facility
priority: 405
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-facility.service
supersedes: []
tags:
  - backend
  - feature
testRequirements:
  unit:
    required: false
    pattern: tests/asset-facility/**/*.test.*
---
# Implement Warranty Expiration Alerts

## Context

Track and alert on facilities with expiring warranties (P1 feature per PRD 6.3.2.4).

## Acceptance Criteria

1. Add service method `getExpiringWarranties(int daysAhead, Long projectId)`:
2. Add REST endpoint `GET /api/asset/facility/warranty-expiring`:
3. Add dashboard widget data endpoint `GET /api/asset/facility/warranty-stats`:
4. Results sorted by warranty_expire_date ascending (most urgent first)
## Technical Notes

- This is P1 feature per PRD but simple to implement
- Can be used by dashboard module for alerts
