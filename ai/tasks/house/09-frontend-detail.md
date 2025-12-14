---
id: house.frontend-detail
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset Detail Page

## Context

The detail page shows comprehensive information about a house asset including maintenance history and attachments.

## Acceptance Criteria

1. Create `/src/views/asset/house/detail.vue` (or drawer component):

2. Implement tabbed layout:

   **Tab 1: Basic Information**
   - Display all asset fields in description list format
   - Asset code prominently displayed
   - Status with colored tag
   - Edit button (with permission)

   **Tab 2: Maintenance Records**
   - List of historical maintenance orders for this asset
   - Columns: Order No, Type, Status, Create Time, Handler
   - Link to order detail
   - "Initiate Maintenance" button

   **Tab 3: Attachments**
   - List of uploaded files
   - Preview for images
   - Download link for documents
   - Upload button (with permission)
   - Delete button (with permission)

3. Show breadcrumb navigation

4. Handle loading and error states

5. Quick actions in header:
   - Edit
   - Copy (create similar)
   - Initiate Maintenance
   - Back to list

6. Display audit info (created by, updated by, timestamps)

## Technical Notes

- Reference: PRD section 6.2.2 point 3
- Can use drawer or full page based on UX preference
