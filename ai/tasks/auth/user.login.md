---
id: auth.user.login
module: auth
priority: 15
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
    pattern: tests/auth/**/*.test.*
---
# Implement login that issues a secure session/JWT and returns current user profile.

## Acceptance Criteria

1. Implement login that issues a secure session/JWT and returns current user profile.
