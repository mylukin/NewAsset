---
id: aiTasks.worker.execute
module: aiTasks
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
    pattern: tests/aiTasks/**/*.test.*
---
# Implement a worker that executes AI tasks with retries, timeouts, and idempotency keys.
> 实现执行 AI 任务的 Worker，支持重试、超时与幂等键。

## Acceptance Criteria

1. Implement a worker that executes AI tasks with retries, timeouts, and idempotency keys.
> 实现执行 AI 任务的 Worker，支持重试、超时与幂等键。 works as expected
