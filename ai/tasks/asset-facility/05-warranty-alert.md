---
id: asset-facility.warranty-alert
module: asset-facility
priority: 405
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.service
tags:
  - backend
  - feature
testRequirements:
  unit:
    required: false
    pattern: "tests/asset-facility/**/*.test.*"
---
# Implement Warranty Expiration Alerts

## Context

Track and alert on facilities with expiring warranties (P1 feature per PRD 6.3.2.4).

## Acceptance Criteria

1. Add service method `getExpiringWarranties(int daysAhead, Long projectId)`:
   - Returns list of facilities with warranty expiring within N days
   - Filterable by project

2. Add REST endpoint `GET /api/asset/facility/warranty-expiring`:
   - Query params: daysAhead (default 30), projectId (optional)
   - Returns paginated list

3. Add dashboard widget data endpoint `GET /api/asset/facility/warranty-stats`:
   - Returns count of facilities by warranty status:
     - expired: warranty_expire_date < today
     - expiring_soon: warranty_expire_date between today and today+30
     - valid: warranty_expire_date > today+30
     - unknown: warranty_expire_date is null

4. Results sorted by warranty_expire_date ascending (most urgent first)

## Technical Notes

- This is P1 feature per PRD but simple to implement
- Can be used by dashboard module for alerts
