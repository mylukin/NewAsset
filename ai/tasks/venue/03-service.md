---
id: venue.service
module: venue
priority: 14
status: failing
version: 1
origin: manual
dependsOn: [venue.mapper, core.code-generator]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/venue/**/*.test.*"
---
# Create Venue Asset Service

## Context

Business logic for venue assets.

## Acceptance Criteria

1. Create `IVenueAssetService` interface:
   - Standard CRUD methods
   - `updateVenueStatus(Long assetId, VenueStatusEnum status)` - update availability

2. Create `VenueAssetServiceImpl`

3. Implement status update logic:
   - Validate status transition
   - Record change in log

## Technical Notes

- Reference: PRD section 6.5.2
