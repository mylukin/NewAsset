---
id: dashboard.charts
module: dashboard
priority: 905
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - dashboard.frontend-layout
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/dashboard/**/*.spec.ts"
    tags:
      - "@dashboard"
---
# Implement Dashboard Charts (Frontend)

## Context

ECharts integration for dashboard visualizations.

## Acceptance Criteria

1. Add ECharts dependency (if not present):
   - npm install echarts vue-echarts

2. Create chart wrapper component `src/views/asset/dashboard/components/ChartWrapper.vue`:
   - Responsive container
   - Loading state
   - Error state with retry
   - Resize handling

3. Create Status Distribution Pie Chart:
   - Component: `StatusPieChart.vue`
   - Props: data (status -> count map), assetType
   - Colors matching 9-state status colors
   - Legend on right side
   - Tooltip on hover showing count and percentage

4. Create Asset Distribution Pie Chart:
   - Component: `AssetTypePieChart.vue`
   - Props: data (type -> count map)
   - Show 5 asset types with distinct colors
   - Click slice -> navigate to that asset type list

5. Create Work Order Trend Line Chart (optional P1):
   - Component: `TrendLineChart.vue`
   - Props: data (date -> count array)
   - X-axis: dates (last 30 days)
   - Y-axis: work order count
   - Two lines: Created vs Completed

6. Chart container styling:
   - White card background
   - Title in card header
   - Minimum height: 300px

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Charts)
- Use vue-echarts v-chart component
