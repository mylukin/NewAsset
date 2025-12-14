---
id: aiTasks.jobs.enqueue
module: aiTasks
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
    pattern: tests/aiTasks/**/*.test.*
---
# Create AI task runs for selected assets (manual trigger and rules-based trigger) and persist job state.
> 为选定资产创建 AI 任务运行（手动触发与规则触发），并持久化任务状态。

## Acceptance Criteria

1. Create AI task runs for selected assets (manual trigger and rules-based trigger) and persist job state.
> 为选定资产创建 AI 任务运行（手动触发与规则触发），并持久化任务状态。 works as expected
