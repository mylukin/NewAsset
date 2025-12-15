---
id: asset-parking.frontend-detail
module: asset-parking
priority: 307
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-parking.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@parking"
---
# Implement Parking Asset Detail Drawer (Frontend)

## Context

Detail drawer component for parking assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/parking/detail.vue`:
   - el-drawer: 800px width
   - Header: Asset code, parking number, status badge, actions

2. Implement 3 tabs:

   **Tab 1: Basic Info**
   - Parking-specific fields: Zone, Number, Type, Area
   - User info: Current User, Plate Number, Contract
   - Financial: Rent Price

   **Tab 2: Maintenance Records**
   - List of work orders linked to this parking spot
   - "Create Work Order" button

   **Tab 3: Attachments**
   - Grid display of files
   - Upload/delete functionality

3. Header actions: Edit, Initiate Maintenance, Delete

4. Status badge with color mapping

## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
