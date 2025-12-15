---
id: maintenance.status-linkage-service
module: maintenance
priority: 705
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - maintenance.status-enum
  - core.status-service
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
---
# Implement Status Linkage Service

## Context

Service to optionally link work order status changes with asset status changes.

## Acceptance Criteria

1. Create `StatusLinkageService`:
2. Implement status restoration logic:
3. Add optional checkbox behavior:
4. Log all status linkage operations:
5. Handle edge cases:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Optional checkbox, default checked)
- PRD: docs/PRD.md Section 6.1.2.3 (资产状态联动)
