---
id: asset-venue.frontend-detail
module: asset-venue
priority: 506
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - asset-venue.frontend-list
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: "e2e/asset/**/*.spec.ts"
    tags:
      - "@venue"
---
# Implement Venue Asset Detail Drawer (Frontend)

## Context

Detail drawer component for venue assets.

## Acceptance Criteria

1. Create detail drawer `src/views/asset/venue/detail.vue`:
   - el-drawer: 800px width
   - Header: Asset code, venue name, availability badge, actions

2. Implement 3 tabs:

   **Tab 1: Basic Info**
   - Venue-specific fields: Type, Capacity, Usage Mode
   - Schedule info: Open Time Description
   - Current availability status with badge

   **Tab 2: Maintenance Records**
   - List of work orders linked to this venue
   - "Create Work Order" button

   **Tab 3: Attachments**
   - Grid display of files
   - Upload/delete functionality

3. Header actions: Edit, Initiate Maintenance, Delete

4. Availability status badge:
   - Green: Available (可预约)
   - Yellow: Occupied (使用中)
   - Orange: Maintaining (维护中)

## Technical Notes

- Similar structure to house detail drawer
- Reference: ai/tasks/asset-house/07-frontend-detail.md
