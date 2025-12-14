---
id: search.assets.query
module: search
priority: 20
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
    pattern: tests/search/**/*.test.*
---
# Provide paginated asset list with filter/sort by type, owner, status, tags, and updated time.
> 提供分页资产列表，支持按类型、归属、状态、标签与更新时间筛选/排序。

## Acceptance Criteria

1. Provide paginated asset list with filter/sort by type, owner, status, tags, and updated time.
> 提供分页资产列表，支持按类型、归属、状态、标签与更新时间筛选/排序。 works as expected
