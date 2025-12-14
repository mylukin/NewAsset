---
id: core.db-config
module: core
priority: 2
status: passing
version: 3
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - infrastructure
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T10:09:23.936Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 24bccb585c03e114b5a49a4b53bd27b6ea22cc5d
  summary: 8/8 criteria satisfied
---
# Configure Dual-Database Infrastructure (SQLite Dev / MySQL Prod)

## Context

The system requires different database backends for development and production environments. SQLite provides faster local setup, simplified CI/CD, and offline development capability. MySQL is used in production for performance and reliability.

## Acceptance Criteria

1. Add SQLite JDBC driver dependency to `ruoyi-asset/pom.xml`
2. Create `application-dev.yml` with SQLite datasource configuration:
3. Create/verify `application-prod.yml` with MySQL datasource configuration
4. Configure MyBatis to handle SQL dialect differences:
5. Create `.gitignore` entry for `data/` directory (SQLite files)
6. Create `data/.gitkeep` to ensure directory exists
7. Verify application starts successfully with both profiles:
8. Document database switching in README or module documentation
## Technical Notes

- Reference: Spring Boot multi-datasource configuration
- SQLite driver: `org.xerial:sqlite-jdbc`
- Use Druid connection pool for both databases
- SQLite file path should be relative to project root for portability
- Profile switching: `-Dspring.profiles.active=dev` or `SPRING_PROFILES_ACTIVE=dev`

## SQL Dialect Differences to Handle

| Feature | MySQL | SQLite |
|---------|-------|--------|
| Auto-increment | `AUTO_INCREMENT` | `AUTOINCREMENT` (optional with INTEGER PRIMARY KEY) |
| Boolean | `TINYINT(1)` | `INTEGER` (0/1) |
| Date/Time | `DATETIME` | `TEXT` (ISO8601) or `INTEGER` (Unix time) |
| Text length | `VARCHAR(n)`, `TEXT` | `TEXT` |
| Foreign keys | Enabled by default | Requires `PRAGMA foreign_keys = ON` |

## MyBatis Dialect Handling Example

```xml
<!-- In mapper XML -->
<if test="_databaseId == 'sqlite'">
  <!-- SQLite specific SQL -->
</if>
<if test="_databaseId == 'mysql'">
  <!-- MySQL specific SQL -->
</if>
```
