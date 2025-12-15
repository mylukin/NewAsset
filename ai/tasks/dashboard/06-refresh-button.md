---
id: dashboard.refresh-button
module: dashboard
priority: 906
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
# Implement Manual Refresh Functionality (Frontend)

## Context

Manual refresh button for dashboard data (no auto-refresh per OVERVIEW.md decision).

## Acceptance Criteria

1. Add refresh button in dashboard header:
   - Position: Top right of dashboard
   - Icon: Refresh/Reload icon
   - Text: "Refresh" (optional, icon-only on mobile)

2. Implement refresh behavior:
   - Click triggers reload of all dashboard data
   - Button shows loading state during fetch
   - Disable button during loading to prevent spam
   - Show "Last updated: HH:mm" timestamp

3. Refresh logic:
   - Call all dashboard API endpoints
   - Update all KPI cards
   - Update all charts
   - Show success toast: "Dashboard refreshed"
   - Show error toast if any request fails

4. Keyboard shortcut (optional):
   - Ctrl+R / Cmd+R to refresh (prevent browser refresh)
   - Or F5 intercept

5. Visual feedback:
   - Spin animation on refresh icon during loading
   - Fade-in effect on data update

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Manual Refresh decision)
- Store last refresh timestamp in component state
