---
id: tests.coverage.smoke
module: tests
priority: 29
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
    pattern: tests/tests/**/*.test.*
---
# Add unit and integration tests covering asset CRUD, RBAC enforcement, and AI task state transitions.
> 添加覆盖资产 CRUD、RBAC 权限控制与 AI 任务状态流转的单元/集成测试。

## Acceptance Criteria

1. Add unit and integration tests covering asset CRUD, RBAC enforcement, and AI task state transitions.
> 添加覆盖资产 CRUD、RBAC 权限控制与 AI 任务状态流转的单元/集成测试。 works as expected
