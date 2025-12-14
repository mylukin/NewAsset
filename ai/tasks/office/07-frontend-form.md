---
id: office.frontend-form
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
# Create Office Asset Form Component

## Context

Form component for creating and editing office assets with dynamic fields based on type.

## Acceptance Criteria

1. Create `/src/views/asset/office/components/OfficeForm.vue`

2. Implement common form fields:
   - Project (required)
   - Asset Name (required)
   - Office Type (required)
   - Use Department
   - Workplace No
   - Use User
   - Duty Person
   - Status
   - Ownership Type
   - Purchase Date
   - Original Value
   - Brand/Model
   - Supplier
   - Warranty Expire Date
   - Remark

3. Implement IT-specific fields (shown when officeType = 'IT_EQUIPMENT'):
   - Serial No / Asset Tag No
   - OS Info
   - Config Description

4. Implement validation rules

5. Support create/edit modes

## Technical Notes

- Reference: PRD section 6.6.2 point 2
- Use v-if for conditional fields
