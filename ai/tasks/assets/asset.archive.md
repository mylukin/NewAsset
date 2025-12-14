---
id: assets.asset.archive
module: assets
priority: 17
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
    pattern: tests/assets/**/*.test.*
---
# Archive or deactivate assets with a reversible state transition and recorded reason.
> 支持资产归档/停用，具备可逆的状态流转并记录原因。

## Acceptance Criteria

1. Archive or deactivate assets with a reversible state transition and recorded reason.
> 支持资产归档/停用，具备可逆的状态流转并记录原因。 works as expected
