---
id: dashboard.kpi-cards
module: dashboard
priority: 904
status: passing
version: 3
origin: spec-workflow
dependsOn:
  - dashboard.frontend-layout
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/dashboard/**/*.spec.ts
    tags:
      - '@dashboard'
---
# Implement KPI Summary Cards (Frontend)

## Context

Reusable KPI card components for dashboard.

## Acceptance Criteria

1. Create KPI card component `src/views/asset/dashboard/components/KpiCard.vue`:
2. KPI cards to implement:
3. Card styling:
4. Loading skeleton when data is fetching
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Summary Cards)
- Use router.push for navigation on click
