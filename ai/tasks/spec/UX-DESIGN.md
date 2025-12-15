# UX Design Specification

## User Journeys

### Journey 1: Asset Registration (Asset Manager)

1. User: Navigates to asset module (e.g., House Asset) → System: Displays asset list
2. User: Clicks "New Asset" button → System: Opens drawer form (800px)
3. User: Fills required fields → System: Auto-generates asset code, validates in real-time
4. User: Uploads attachments → System: Shows progress, preview thumbnails
5. User: Clicks "Save" → System: Creates asset, shows success toast, returns to list
6. User: (Optional) Clicks "Copy Asset" → System: Pre-fills form for batch entry

### Journey 2: Work Order Lifecycle

1. Asset Manager: Views asset detail, clicks "Initiate Maintenance"
2. Asset Manager: Fills work order form, assigns to Maintenance Staff
3. Maintenance Staff: Views "My Work Orders" dashboard
4. Maintenance Staff: Clicks "Accept" → Status: In Progress
5. Maintenance Staff: Completes work, uploads photos, submits
6. Asset Manager: Reviews and clicks "Verify & Close"

### Journey 3: Excel Bulk Import

1. User: Clicks "Import" → Opens import modal
2. User: Downloads template → Excel with column headers
3. User: Uploads filled file → System validates each row
4. User: Reviews errors → Highlights issues per row
5. User: Clicks "Import Valid Rows" → System imports with auto-codes

### Journey 4: Dashboard Overview (Management)

1. User: Logs in → System displays role-based dashboard
2. User: Views summary cards (total assets, vacancy rate, pending orders)
3. User: Clicks metric card → Navigates to filtered list
4. User: Views charts (status distribution, trends)

---

## Screens

### Screen 1: Role-Based Dashboard

- **Components:**
  - Header: Project name (fixed), user info, notifications, logout
  - Sidebar: Navigation menu per role permissions
  - Summary Cards: 4-6 KPI cards with icons and trend indicators
  - Quick Actions: Role-specific buttons
  - Charts: Status distribution pie, trend line chart
  - Recent Activity: Table of recent work orders/changes

### Screen 2: Asset List Page (5 modules)

- **Components:**
  - Search Bar: Collapsible advanced search (code, name, status, location, date)
  - Action Bar: "New Asset", "Import", "Export", "Batch Delete"
  - Data Table (el-table):
    - Columns: Checkbox, Asset Code, Name, Status (badge), Location, Responsible Person, Actions
    - Row Actions: View, Edit, Initiate Maintenance
  - Pagination: el-pagination with size selector (20/50/100)
  - Status Filter Tabs: Quick filter above table

### Screen 3: Asset Detail (Drawer)

- **Components:**
  - Header: Asset code (large), name, status badge, action buttons
  - Tab Navigation (el-tabs):
    - Tab 1 - Basic Info: Form layout with properties
    - Tab 2 - Maintenance Records: Timeline of work orders
    - Tab 3 - Attachments: Grid with preview, upload button
  - Status History: Collapsible timeline

### Screen 4: Asset Form (Create/Edit)

- **Components:**
  - Form (el-form): Grouped sections
    - Required: Name, Type, Location (cascader), Responsible Person
    - Auto-generated: Asset Code (displayed, not editable)
    - Optional: Description, Specifications, Purchase Date, Value
  - Attachment Upload (el-upload): Drag-drop zone
  - Footer: "Save", "Save & Add Another", "Cancel"

### Screen 5: Work Order List

- **Components:**
  - View Tabs: "All", "My Created", "Assigned to Me"
  - Search/Filter: WO number, asset code, status, priority, date
  - Data Table: WO Number, Asset, Type, Priority (badge), Status, Assignee, Actions
  - Kanban Toggle: Optional board view by status

### Screen 6: Work Order Detail

- **Components:**
  - Header: WO Number, Priority badge, Status badge, Actions
  - Info Panel: Two-column work order details
  - Linked Asset Card: Clickable asset summary
  - Status Timeline (el-timeline): Visual state transitions
  - Action Panel: Context-sensitive buttons per role
  - Completion Form: Notes, photos (when submitting)
  - Comment Thread: Discussion history

### Screen 7: Import Modal

- **Components:**
  - Step Indicator: 3-step wizard
  - Step 1: Download Template button
  - Step 2: Upload Zone (drag-drop)
  - Step 3: Validation Results Table
  - Actions: "Import Valid Rows", "Re-upload", "Cancel"

### Screen 8: System Configuration (Admin)

- **Components:**
  - Sub-navigation: Users, Roles, Departments, Dictionaries
  - Standard CRUD tables

---

## Interactions

| Trigger | Response | Timing |
|---------|----------|--------|
| Click "New Asset" | Open drawer (800px) from right | 200ms slide-in |
| Submit form with errors | Highlight fields, scroll to first error | Immediate |
| Form submit success | Close drawer, success toast, refresh list | Toast: 3s |
| Click asset code | Open detail drawer | 200ms |
| Click "Copy Asset" | Open form pre-filled (clear code) | 200ms |
| Change status filter | Filter table with loading | 300ms debounce |
| Upload file | Progress bar, preview on complete | Real-time |
| Click "Delete" | Confirmation dialog | Requires confirm |
| Work order action | Confirmation, status update | 200ms + API |
| Excel upload complete | Show validation results | Up to 5s |
| Hover status badge | Tooltip with description | 200ms delay |

---

## Error Handling

| Scenario | Display | Recovery |
|----------|---------|----------|
| Form validation failed | Red border, inline error text | Fix fields |
| API network error | Toast + retry button | Retry or auto-retry |
| API server error | Toast with error code | Retry or contact admin |
| Excel import row error | Table with row/column/error | Fix and re-upload |
| File upload failed | Red status with retry icon | Retry or remove |
| Session expired | Modal "Please login again" | Redirect to login |
| Permission denied | Toast "No permission" | Contact admin |
| Delete blocked (open WO) | Warning listing linked records | Close WOs first |
| Concurrent edit conflict | Dialog "Modified by another user" | Reload or overwrite |

---

## 9-State Status Machine Visual

| Status | Badge Color | Icon |
|--------|-------------|------|
| Under Construction | Gray (#909399) | Construction |
| Available - Self Use | Blue (#409EFF) | Checkmark |
| Available - Rental | Cyan (#00BCD4) | Key |
| Available - Idle | Light Green (#67C23A) | Circle |
| Suspended | Purple (#9B59B6) | Pause |
| Fault | Red (#F56C6C) | Warning |
| Maintaining | Orange (#E6A23C) | Wrench |
| Planned Scrap | Red-orange (#F56C6C) | Alert |
| Scrapped | Dark Gray (#606266) | X mark |

---

## Accessibility (WCAG 2.1 AA)

- **Keyboard:** All elements reachable via Tab, Enter/Space activates, Escape closes modals
- **Screen Reader:** Proper heading hierarchy, ARIA labels on icon buttons, live regions for toasts
- **Color Contrast:** 4.5:1 minimum, status includes text/icon (not color alone)
- **Focus Management:** Visible focus ring, focus trap in modals, focus returns on close

---

## Responsive Design

| Breakpoint | Sidebar | Dashboard | Tables | Drawers |
|------------|---------|-----------|--------|---------|
| 1920px+ | Full (240px) | 4 cards/row | All columns | 800px |
| 1440px | Collapsible | 4 cards/row | Scroll | 600px |
| 1280px | Default collapsed | 2-3 cards/row | Some columns hidden | Full page option |
| 1024px (min) | Overlay | 2 cards/row | Essential only | Near full-width |
