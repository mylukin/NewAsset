---
id: facility.warranty-reminder
module: facility
priority: 28
status: failing
version: 16
origin: manual
dependsOn:
  - facility.service
supersedes: []
tags:
  - service
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
---
# Implement Warranty Expiry Reminder for Facilities (P1)

## Context

Facility assets with approaching warranty expiry dates should be listed for proactive maintenance planning.

## Acceptance Criteria

1. Add mapper method: `selectExpiringFacilities(int daysAhead)`
2. Add service method: `getExpiringFacilities(int daysAhead)`
3. Add controller endpoint: `GET /asset/facility/expiring`
4. Return list of facilities with warranty expiring within N days
5. Include expiry date and days remaining in response
6. Support project filter

## Technical Notes

- Reference: PRD Section 6.3.2 Point 4
- Query: WHERE warranty_expire_date BETWEEN NOW() AND NOW() + INTERVAL N DAY
- Default: 30 days ahead
