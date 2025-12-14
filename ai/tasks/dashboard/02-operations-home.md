---
id: dashboard.operations-home
module: dashboard
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-list]
supersedes: []
tags: [feature, dashboard, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Operations Staff Dashboard

## Context

Operations/maintenance staff see a task-focused dashboard with their pending work orders and personal statistics.

## Acceptance Criteria

1. Backend API `GET /asset/dashboard/operations`:
   - Returns OperationsDashboardVO
   - Filtered by current user as handler
2. Metrics for current user:
   - Pending orders (WAIT_ACCEPT)
   - In-progress orders (PROCESSING)
   - Completed this month
   - Total completed (all time)
3. Pending orders list:
   - All orders assigned to current user, not closed
   - Sorted by priority then required finish time
   - Shows overdue indicator
4. Frontend page:
   - Summary cards at top:
     - Waiting for me to accept
     - Currently processing
     - Completed this month
   - Main table: My pending orders
     - Priority-based row highlighting
     - Quick action buttons (Accept, Process)
   - Secondary section: Recently completed

## Technical Notes

- currentUser from SecurityUtils
- Priority colors: urgent=red, important=orange, normal=default
- Consider mobile-optimized view
