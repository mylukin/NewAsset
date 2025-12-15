---
id: asset-facility.frontend-detail
module: asset-facility
priority: 407
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-facility.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@facility"
---
# Implement Facility Asset Detail Drawer (Frontend)

## Context

Detail drawer component for facility assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/facility/detail.vue`:
   - el-drawer: 800px width
   - Header: Asset code, facility name, status badge, actions

2. Implement 3 tabs:

   **Tab 1: Basic Info**
   - Facility-specific fields: Type, Device Code, Install Location
   - Warranty info: Warranty Expire Date (with status indicator)
   - Maintenance: Maintenance Org, Maintenance Phone

   **Tab 2: Maintenance Records**
   - List of work orders linked to this facility
   - "Create Work Order" button

   **Tab 3: Attachments**
   - Grid display of files
   - Upload/delete functionality

3. Header actions: Edit, Initiate Maintenance, Delete

4. Warranty status badge:
   - Green: Valid (>30 days)
   - Orange: Expiring Soon (<=30 days)
   - Red: Expired

## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
