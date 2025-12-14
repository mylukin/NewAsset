---
id: house.detail
module: house
priority: 4
status: failing
version: 1
origin: manual
dependsOn: [house.entity, core.attachment-service]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset Detail View

## Context

The detail view shows comprehensive information about a house asset, including its maintenance history and attachments. Users can initiate maintenance requests from here.

## Acceptance Criteria

1. Backend API `GET /asset/house/{id}`:
   - Returns AssetHouseDetailVO with:
     - All base asset fields
     - House extension fields
     - Project/department names
     - Maintenance history summary (count, last date)
     - Attachment list
   - Permission: @PreAuthorize("asset:house:query")
2. Frontend detail drawer/page:
   - Tab 1: Basic Information
     - Card layout with field groups
     - Status badge with color
     - Asset code prominently displayed
   - Tab 2: Maintenance Records
     - Table of related work orders
     - Link to order detail
     - Filter by type/status
   - Tab 3: Attachments
     - Upload button
     - List with preview, download, delete
     - Image lightbox viewer
3. Action buttons:
   - Edit (opens edit dialog)
   - Initiate Maintenance (opens order form)
   - Delete (with confirmation)
4. Change history section (collapsible):
   - Shows key field changes from t_asset_change_log
   - Old value, new value, operator, time

## Technical Notes

- Use el-drawer for side panel detail
- Tabs with lazy loading for performance
- Maintenance records fetched separately
