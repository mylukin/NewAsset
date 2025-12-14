---
id: db.schema.bootstrap
module: db
priority: 11
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
# Define initial database schema for users, roles, assets, tags, attachments, AI tasks, and audit logs.
> 定义初始数据库结构：用户、角色、资产、标签、附件、AI 任务与审计日志。

## Acceptance Criteria

1. Database schema supports both SQLite (primary) and MySQL (alternative)
   > 数据库结构同时支持 SQLite（主）和 MySQL（备选）
2. Schema includes tables for: users, roles, assets, tags, attachments, AI tasks, and audit logs
   > 结构包含表：用户、角色、资产、标签、附件、AI 任务与审计日志
3. Migration scripts are provided for both database types
   > 为两种数据库类型提供迁移脚本
4. Database-agnostic SQL is used where possible
   > 尽可能使用数据库无关的 SQL

## Technical Notes

- **SQLite**: Primary database for development, testing, and small deployments
- **MySQL**: Alternative for production and larger scale deployments
- Use compatible data types across both databases
- Avoid database-specific functions unless necessary
