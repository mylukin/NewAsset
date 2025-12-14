---
id: facility.frontend-form
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
# Create Facility Asset Form Component

## Context

Form component for creating and editing facility assets.

## Acceptance Criteria

1. Create `/src/views/asset/facility/components/FacilityForm.vue`

2. Implement form fields:

   **Basic Info:**
   - Project (required)
   - Asset Name (required)
   - Building, Floor
   - Location Description
   - Ownership Type
   - Use Department
   - Duty Person
   - Status

   **Facility Specific:**
   - Facility Type (required, dropdown)
   - Install Position Description
   - Equipment No (original manufacturer number)
   - Maintenance Org
   - Maintenance Phone

   **Value & Warranty:**
   - Purchase Date
   - Original Value
   - Warranty Expire Date
   - Brand/Model
   - Supplier

   **Other:**
   - Remark

3. Implement validation rules

4. Support create/edit modes

## Technical Notes

- Reference: PRD section 6.3.2
