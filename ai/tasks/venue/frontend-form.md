---
id: venue.frontend-form
module: venue
priority: 15
status: failing
version: 1
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - P0
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create Venue Asset Form Component

## Acceptance Criteria

1. Create `/src/views/asset/venue/components/VenueForm.vue`
2. Implement form fields:
3. Implement validation rules
4. Support create/edit modes
