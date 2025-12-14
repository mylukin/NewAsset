---
id: files.attachment.upload
module: files
priority: 22
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
# Upload and attach a file to an asset, storing metadata and enforcing size/type limits.

## Acceptance Criteria

1. Upload and attach a file to an asset, storing metadata and enforcing size/type limits.
