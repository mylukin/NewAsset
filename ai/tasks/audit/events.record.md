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
> 对每次资产创建/更新/归档以及管理员权限变更记录审计事件。

## Acceptance Criteria

1. Record an audit event for every asset create/update/archive and for admin permission changes.
> 对每次资产创建/更新/归档以及管理员权限变更记录审计事件。 works as expected
