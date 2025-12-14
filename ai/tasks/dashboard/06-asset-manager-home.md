---
id: dashboard.asset-manager-home
module: dashboard
priority: 16
status: failing
version: 1
origin: manual
dependsOn: [dashboard.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Asset Manager Dashboard Page

## Context

Dashboard for asset managers showing assets needing attention and recent additions.

## Acceptance Criteria

1. Create `/src/views/dashboard/AssetManagerHome.vue`

2. Implement "Assets Needing Attention" section:
   - Assets with missing key fields (e.g., no duty person, no purchase date)
   - Click to edit and complete

3. Implement "Recently Added Assets" section:
   - Last 10 assets added
   - Columns: Code, Name, Type, Create Time
   - Click to view detail

4. Implement asset count by category chart:
   - Bar or pie chart
   - Categories: House, Parking, Facility, Venue, Office

5. Implement quick actions:
   - Add new asset (with type selection)
   - View all assets

6. Project selector for filtering

## Technical Notes

- Reference: PRD section 7.1 point 3
