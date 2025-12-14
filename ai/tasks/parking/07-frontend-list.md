---
id: parking.frontend-list
module: parking
priority: 9
status: failing
version: 1
origin: manual
dependsOn: [parking.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Parking Asset List Page

## Context

Parking asset list page with filtering, statistics, and CRUD operations.

## Acceptance Criteria

1. Create `/src/views/asset/parking/index.vue`

2. Implement search filters:
   - Project selector
   - Parking Zone (地上/地下/编号)
   - Parking No
   - Parking Type (固定/临停)
   - Status selector
   - Search/Reset buttons

3. Implement action toolbar with permissions

4. Implement data table with columns:
   - Asset Code
   - Parking No
   - Project
   - Zone
   - Type
   - Status
   - Current User
   - Plate No
   - Rent Price
   - Actions

5. Optionally show statistics cards above table

## Technical Notes

- Reference: PRD section 6.4.2
