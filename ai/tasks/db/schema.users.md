---
id: db.schema.users
module: db
priority: 13
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
# Define users/roles tables to support authentication and authorization.
> 定义用户/角色表以支持认证和授权。

## Acceptance Criteria

1. Users and roles tables support both SQLite (primary) and MySQL (alternative)
   > 用户和角色表同时支持 SQLite（主）和 MySQL（备选）
2. Tables support authentication (password hash, tokens) and authorization (roles, permissions)
   > 表支持认证（密码哈希、令牌）和授权（角色、权限）
3. Migration scripts are provided for both database types
   > 为两种数据库类型提供迁移脚本
4. Foreign key relationships are properly defined
   > 外键关系正确定义

## Technical Notes

- **SQLite**: Primary database (enable foreign keys with `PRAGMA foreign_keys = ON`)
- **MySQL**: Alternative database (InnoDB engine for foreign key support)
- Password fields should use secure hash storage
- Consider role-based access control (RBAC) structure
