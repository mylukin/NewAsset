---
id: house.frontend-statistics
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.statistics, house.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset Statistics View

## Context

Display statistical overview of house assets with key metrics and visualizations.

## Acceptance Criteria

1. Create `/src/views/asset/house/statistics.vue` (or integrate into list page):

2. Implement statistics cards:
   - Total House Count (总房源数量)
   - Self-Use Count & Ratio (自用数量/占比)
   - Rented Count & Ratio (出租数量/占比)
   - Idle Count & Ratio (空置数量/占比) - highlight if > threshold

3. Add project selector to filter statistics

4. Implement pie chart for usage distribution:
   - Self-use vs Rented vs Idle
   - Use ECharts or similar library

5. Optionally add trend line if historical data available (P1)

6. Refresh button to reload statistics

7. Style cards with appropriate colors:
   - Green for positive indicators
   - Red/Yellow for concerning metrics (high vacancy)

8. Make statistics cards clickable to filter list

## Technical Notes

- Reference: PRD section 6.2.2 point 4
- Consider using dashboard component library
