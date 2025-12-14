---
id: api.rest.assets
module: api
priority: 26
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
# Expose REST endpoints for asset CRUD, attachments, tags, and AI task triggers with request validation.
> 提供资产增删改查、附件、标签与 AI 任务触发的 REST 接口，并做请求校验。

## Acceptance Criteria

1. Expose REST endpoints for asset CRUD, attachments, tags, and AI task triggers with request validation.
> 提供资产增删改查、附件、标签与 AI 任务触发的 REST 接口，并做请求校验。 works as expected
