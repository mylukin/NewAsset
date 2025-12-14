---
id: core.asset-status-enum
module: core
priority: 2
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - backend
  - enum
testRequirements:
  unit:
    required: true
    pattern: tests/core/**/*.test.*
---
# Create Asset Status Enum

## Acceptance Criteria

1. Create `AssetStatusEnum` in `com.ruoyi.asset.domain.enums` with values:
2. Include code and description fields for each enum value
3. Provide utility methods:
