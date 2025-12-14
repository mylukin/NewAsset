---
id: dashboard.asset-manager-home
module: dashboard
priority: 3
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity, maintenance.order-list]
supersedes: []
tags: [feature, dashboard, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Asset Manager Dashboard

## Context

Asset managers see a dashboard focused on asset data quality and recent changes, helping them maintain accurate asset records.

## Acceptance Criteria

1. Backend API `GET /asset/dashboard/assetManager`:
   - Returns AssetManagerDashboardVO
   - Filtered by user's authorized projects
2. Content:
   - Incomplete assets list:
     - Assets missing key fields (e.g., no responsible person, no value)
     - Grouped by asset type
   - Recently added assets:
     - Last 10 assets added
     - Shows code, name, type, creator, time
   - Asset count by type:
     - Pie/bar chart of asset distribution
3. Frontend page:
   - Alert section: Assets needing attention
     - Click to edit asset
   - Recent activity section
   - Asset distribution chart
4. Data quality checks:
   - dutyUserId is null
   - originalValue is null or 0
   - status is UNDER_CONSTRUCTION for >1 year

## Technical Notes

- Data quality rules can be configurable
- Consider periodic data quality reports
- Link to batch update for fixes
