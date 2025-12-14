---
id: venue.frontend-form
module: venue
priority: 15
status: failing
version: 1
origin: manual
dependsOn: [venue.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Venue Asset Form Component

## Context

Form component for creating and editing venue assets.

## Acceptance Criteria

1. Create `/src/views/asset/venue/components/VenueForm.vue`

2. Implement form fields:
   - Project (required)
   - Asset Name / Venue Name (required)
   - Location (Building, Floor)
   - Venue Type (required)
   - Capacity (required)
   - Use Mode (required)
   - Open Time Description (text)
   - Current Venue Status
   - Ownership Type
   - Duty Person
   - Remark

3. Implement validation rules

4. Support create/edit modes

## Technical Notes

- Reference: PRD section 6.5.2 point 2
