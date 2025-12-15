---
id: dashboard.frontend-layout
module: dashboard
priority: 903
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - dashboard.controller
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
# Implement Dashboard Layout (Frontend)

## Context

Vue dashboard page with role-based layout.

## Acceptance Criteria

1. Create API file `src/api/asset/dashboard.js`:
   - getDashboardStats(projectId)
   - getRecentOrders(projectId, limit)
   - getAssetDistribution(projectId)
   - getStatusDistribution(projectId, assetType)

2. Create dashboard page `src/views/asset/dashboard/index.vue`:
   - Fixed header with project name and refresh button
   - Responsive grid layout (el-row, el-col)

3. Role-based layout variants:

   **Management/Operations Layout:**
   - Row 1: 4 KPI summary cards (total assets, vacancy rate, pending WO, completed WO)
   - Row 2: Status distribution pie + Work order trend chart
   - Row 3: Recent activity table

   **Maintenance Staff Layout:**
   - Row 1: 2 cards (My Pending, My Completed This Month)
   - Row 2: My Work Order Queue table (top 10)
   - Simplified view, no charts

   **Asset Manager Layout:**
   - Row 1: 4 KPI cards (asset counts by type)
   - Row 2: Asset distribution pie + Status distribution pie
   - Row 3: Recent asset additions table

4. Role detection from Vuex store user info

5. Loading states for each section

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 1 (Role-Based Dashboard)
- Use v-if for role-based rendering
