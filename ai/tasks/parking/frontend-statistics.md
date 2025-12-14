---
id: parking.frontend-statistics
module: parking
priority: 9
status: failing
version: 1
origin: manual
dependsOn:
  - parking.statistics
  - parking.frontend-api
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
# Create Parking Asset Statistics View

## Acceptance Criteria

1. Create statistics view (integrated or separate page)
2. Implement statistics cards:
3. Add project and zone selectors
4. Implement bar/pie chart for zone comparison
5. Show utilization rate by zone
