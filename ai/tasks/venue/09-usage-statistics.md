---
id: venue.usage-statistics
module: venue
priority: 47
status: failing
version: 10
origin: manual
dependsOn:
  - venue.service
supersedes: []
tags:
  - statistics
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
---
# Implement Venue Usage Statistics (P1)

## Context

Track venue usage through manual records for statistics before reservation system integration.

## Acceptance Criteria

1. Create `t_asset_venue_usage` table:
   - venueId, usageDate, timeSlot, purpose, attendeeCount, createBy, createTime
2. Create entity, mapper, service for venue usage records
3. Add endpoints:
   - POST /asset/venue/{id}/usage - record usage
   - GET /asset/venue/{id}/usage - get usage history
   - GET /asset/venue/usage/statistics - aggregate stats
4. Statistics include: usage frequency, popular time slots, average attendance

## Technical Notes

- Reference: PRD Section 6.5.2 Point 4
- Pattern: CRUD with aggregation
- Future: Integrate with reservation system
