---
id: audit.events.record
module: audit
priority: 21
status: failing
version: 1
origin: init-auto
dependsOn: []
supersedes: []
tags:
  - inferred
testRequirements:
  unit:
    required: false
    pattern: tests/audit/**/*.test.*
---
# Record an audit event for every asset create/update/archive and for admin permission changes.

## Acceptance Criteria

1. Record an audit event for every asset create/update/archive and for admin permission changes.
