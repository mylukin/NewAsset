---
id: dashboard.kpi-cards
module: dashboard
priority: 904
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
# Implement KPI Summary Cards (Frontend)

## Context

Reusable KPI card components for dashboard.

## Acceptance Criteria

1. Create KPI card component `src/views/asset/dashboard/components/KpiCard.vue`:
   - Props: title, value, suffix, icon, trend, color
   - Show icon on left, title + value on right
   - Optional trend indicator (up/down arrow with percentage)
   - Clickable to navigate to related list

2. KPI cards to implement:

   **Total Assets Card:**
   - Icon: Building icon
   - Value: Total asset count
   - Click -> Asset list (all types)

   **Vacancy Rate Card:**
   - Icon: Home icon
   - Value: X.X%
   - Subtitle: "X idle / Y total houses"
   - Click -> House asset list filtered by idle

   **Pending Work Orders Card:**
   - Icon: Warning icon
   - Value: Count
   - Color: Orange if > 0
   - Click -> Work order list (pending status)

   **Completed This Month Card:**
   - Icon: Check icon
   - Value: Count
   - Color: Green
   - Click -> Work order list (closed, this month)

3. Card styling:
   - White background with shadow
   - Rounded corners
   - Hover effect with slight lift
   - Responsive sizing

4. Loading skeleton when data is fetching

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Summary Cards)
- Use router.push for navigation on click
