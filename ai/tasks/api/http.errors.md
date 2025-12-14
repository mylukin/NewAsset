---
id: api.http.errors
module: api
priority: 24
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
    pattern: tests/api/**/*.test.*
---
# Implement a consistent API error format (status, code, message, requestId) across all endpoints.

## Acceptance Criteria

1. Implement a consistent API error format (status, code, message, requestId) across all endpoints.
