---
id: house.frontend-statistics
module: house
priority: 19
status: failing
version: 4
origin: manual
dependsOn:
  - house.frontend-api
  - house.statistics
supersedes: []
tags:
  - frontend
  - statistics
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
---
# Create House Asset Statistics View

## Context

Statistics dashboard showing house asset metrics like vacancy rates and usage distribution.

## Acceptance Criteria

1. Create statistics component (can be in index.vue or separate)
2. Display metric cards:
   - Total house count
   - Self-use count with percentage
   - Rent count with percentage
   - Idle count with vacancy rate
3. Add project selector to filter statistics
4. Use visual indicators (progress bars or pie charts)
5. Auto-refresh on project change
6. Handle loading and error states

## Technical Notes

- Reference: PRD Section 6.2.2 Point 4
- Pattern: Dashboard cards with metrics
- Optional: Use ECharts for pie chart visualization
