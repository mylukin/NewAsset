---
id: facility.frontend-list
module: facility
priority: 13
status: failing
version: 1
origin: manual
dependsOn: [facility.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Facility Asset List Page

## Context

The facility asset list page with filtering and CRUD operations.

## Acceptance Criteria

1. Create `/src/views/asset/facility/index.vue`

2. Implement search filters:
   - Project selector
   - Building input
   - Floor input
   - Facility Type selector (dict: facility_type)
   - Status selector
   - Search/Reset buttons

3. Implement action toolbar:
   - Add, Export, Batch Delete buttons with permissions

4. Implement data table with columns:
   - Asset Code
   - Asset Name
   - Project
   - Location (Building/Floor)
   - Facility Type
   - Equipment No
   - Status
   - Maintenance Org
   - Warranty Expire Date
   - Actions

5. Implement pagination

6. Row actions: View, Edit, Delete, Initiate Maintenance

## Technical Notes

- Reference: PRD section 6.3.2
- Same pattern as house list page
