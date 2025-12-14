---
id: house.frontend-statistics
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn:
  - house.statistics
  - house.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - P0
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create House Asset Statistics View

## Acceptance Criteria

1. Create `/src/views/asset/house/statistics.vue` (or integrate into list page):
2. Implement statistics cards:
3. Add project selector to filter statistics
4. Implement pie chart for usage distribution:
5. Optionally add trend line if historical data available (P1)
6. Refresh button to reload statistics
7. Style cards with appropriate colors:
8. Make statistics cards clickable to filter list
