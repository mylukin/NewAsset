---
id: asset-office.frontend-detail
module: asset-office
priority: 606
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-office.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@office"
---
# Implement Office Asset Detail Drawer (Frontend)

## Context

Detail drawer component for office assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/office/detail.vue`:
   - el-drawer: 800px width
   - Header: Asset code, asset name, status badge, actions

2. Implement 3 tabs:

   **Tab 1: Basic Info**
   - Office-specific fields: Type, Serial Number, Configuration
   - Assignment info: Use Department, User Name
   - Base asset fields: Responsible Person, Purchase Date, Original Value

   **Tab 2: Maintenance Records**
   - List of work orders linked to this office asset
   - "Create Work Order" button

   **Tab 3: Attachments**
   - Grid display of files
   - Upload/delete functionality

3. Header actions: Edit, Initiate Maintenance, Delete

4. Status badge with color mapping (same as 9-state machine)

## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
