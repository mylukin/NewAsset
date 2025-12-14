---
id: office.service
module: office
priority: 10
status: failing
version: 1
origin: manual
dependsOn: [office.mapper, core.code-generator]
supersedes: []
tags: [backend, service, P0]
testRequirements:
  unit:
    required: true
    pattern: "tests/office/**/*.test.*"
---
# Create Office Asset Service

## Context

Business logic for office assets including condition rate statistics.

## Acceptance Criteria

1. Create `IOfficeAssetService` interface:
   - Standard CRUD methods
   - `OfficeAssetStatistics getConditionStatistics(Long projectId, Long deptId)` - P1

2. Create `OfficeAssetServiceImpl`

3. Implement condition statistics (P1):
   - Total asset count
   - Fault/Maintaining count
   - Condition rate = (total - fault) / total

## Technical Notes

- Reference: PRD section 6.6.2
