---
id: files.attachment.download
module: files
priority: 23
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
    pattern: tests/files/**/*.test.*
---
# Download an asset attachment with authorization and audit logging.

## Acceptance Criteria

1. Download an asset attachment with authorization and audit logging.
