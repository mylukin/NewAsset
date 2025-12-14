---
id: parking.frontend-statistics
module: parking
priority: 38
status: failing
version: 12
origin: manual
dependsOn:
  - parking.frontend-api
  - parking.statistics
supersedes: []
tags:
  - frontend
  - statistics
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
---
# Create Parking Asset Statistics View

## Context

Statistics dashboard for parking utilization metrics.

## Acceptance Criteria

1. Create statistics component
2. Display metric cards:
   - Total parking count
   - Self-use count with percentage
   - Rent count with percentage
   - Idle count with vacancy rate
3. Filter by project and parking zone
4. Visual indicators (progress bars or charts)
5. Auto-refresh on filter change

## Technical Notes

- Reference: PRD Section 6.4.2 Point 4
- Pattern: Dashboard cards
- Optional: ECharts visualization
