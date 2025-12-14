---
id: office.frontend-list
module: office
priority: 11
status: failing
version: 1
origin: manual
dependsOn: [office.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Office Asset List Page

## Context

Office asset list page with filtering by type and department.

## Acceptance Criteria

1. Create `/src/views/asset/office/index.vue`

2. Implement search filters:
   - Project selector
   - Use Department selector
   - Office Type (家具/IT设备/其他)
   - Duty Person selector
   - Status selector
   - Search/Reset buttons

3. Implement action toolbar with permissions

4. Implement data table with columns:
   - Asset Code
   - Asset Name
   - Project
   - Department
   - Office Type
   - Workplace No
   - Use User
   - Serial No (for IT)
   - Status
   - Duty Person
   - Actions

5. Conditional columns based on office type

## Technical Notes

- Reference: PRD section 6.6.2
