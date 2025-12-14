---
id: parking.frontend-form
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
# Create Parking Asset Form Component

## Context

Form component for creating and editing parking assets.

## Acceptance Criteria

1. Create `/src/views/asset/parking/components/ParkingForm.vue`

2. Implement form fields:
   - Project (required)
   - Asset Name
   - Parking No (required)
   - Parking Zone (required)
   - Parking Type (required)
   - Area (optional)
   - Current User (optional)
   - Plate No (optional)
   - Rent Price (optional)
   - Contract No (optional)
   - Ownership Type
   - Duty Person
   - Status
   - Remark

3. Implement validation rules

4. Support create/edit modes

## Technical Notes

- Reference: PRD section 6.4.2 point 2
