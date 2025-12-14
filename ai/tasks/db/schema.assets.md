---
id: db.schema.assets
module: db
priority: 12
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
    pattern: tests/db/**/*.test.*
---
# Define database schema for assets (name, type, tags, owner, status, timestamps) with migrations.
> 定义资产数据库结构（名称、类型、标签、归属、状态、时间戳），包含迁移脚本。

## Acceptance Criteria

1. Asset table schema supports both SQLite (primary) and MySQL (alternative)
   > 资产表结构同时支持 SQLite（主）和 MySQL（备选）
2. Fields include: name, type, tags, owner, status, timestamps
   > 字段包含：名称、类型、标签、归属、状态、时间戳
3. Migration scripts work on both database types
   > 迁移脚本在两种数据库类型上均可运行
4. Proper indexes are created for common queries
   > 为常见查询创建适当的索引

## Technical Notes

- **SQLite**: Primary database (default for development)
- **MySQL**: Alternative database (optional for production)
- Use `TEXT` instead of `VARCHAR` for SQLite compatibility
- Use `INTEGER PRIMARY KEY` for SQLite auto-increment
