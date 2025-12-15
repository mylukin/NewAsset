---
id: maintenance.frontend-list
module: maintenance
priority: 708
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.controller
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/maintenance/**/*.spec.ts"
    tags:
      - "@maintenance"
---
# Implement Work Order List Page (Frontend)

## Context

Vue page for work order list with view tabs and filters.

## Acceptance Criteria

1. Create API file `src/api/asset/maintenance.js`:
   - listMaintOrder(query)
   - getMaintOrder(id)
   - createMaintOrder(data)
   - assignMaintOrder(id, handlerId)
   - acceptMaintOrder(id)
   - completeMaintOrder(id, data)
   - confirmMaintOrder(id, data)
   - cancelMaintOrder(id, reason)
   - getMaintLog(orderId)
   - getMaintOrderByAsset(assetId)

2. Create list page `src/views/asset/maintenance/index.vue`:
   - View tabs: All, My Created, Assigned to Me
   - Search bar: Project, asset code, status, priority, handler, date range
   - Action bar: New Work Order
   - Data table:
     - Columns: WO Number, Asset Code, Fault Type, Priority (badge), Status (badge), Handler, Due Date, Actions
     - Row Actions: View, Assign, Accept, Complete, Confirm, Cancel (based on status)

3. Status badges with colors:
   - WAIT_ASSIGN: Gray
   - WAIT_ACCEPT: Blue
   - PROCESSING: Orange
   - WAIT_CONFIRM: Purple
   - CLOSED: Green
   - CANCELED: Red

4. Priority badges:
   - normal: Gray
   - important: Orange
   - urgent: Red

5. Permission controls with v-hasPermi

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md Screen 5 (Work Order List)
