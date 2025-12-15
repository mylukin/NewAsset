---
id: asset-house.frontend-detail
module: asset-house
priority: 207
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-house.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@house"
---
# Implement House Asset Detail Drawer (Frontend)

## Context

Detail drawer component showing full house asset information with tabs.

## Acceptance Criteria

1. Create detail drawer component `src/views/asset/house/detail.vue`:
   - el-drawer: 800px width, from right
   - Header: Asset code (large), name, status badge, action buttons

2. Implement 3 tabs (el-tabs):

   **Tab 1: Basic Info**
   - Two-column layout for properties
   - Read-only display of all fields
   - Edit button switches to edit mode (inline editing)
   - Fields grouped: Basic, Location, Ownership, Financial, House-specific

   **Tab 2: Maintenance Records**
   - List of work orders linked to this asset
   - Columns: WO Number, Title, Status, Created Date, Handler
   - Click to view work order detail
   - "Create Work Order" button at top

   **Tab 3: Attachments**
   - Grid display of uploaded files
   - Image preview on click
   - Upload button (drag-drop zone)
   - Delete button on each file

3. Header actions:
   - Edit button (opens edit mode or form drawer)
   - Initiate Maintenance button
   - Delete button (with confirmation)

4. Status badge:
   - Color based on status enum
   - Click to change status (if permitted)

5. Close drawer returns to list page

## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset Detail Drawer)
- Uses el-drawer, el-tabs, el-descriptions components
