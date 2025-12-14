---
id: core.db-config
module: core
priority: 2
status: passing
version: 22
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
  verifiedAt: '2025-12-14T11:55:15.446Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: c5b2a3548efc783d745b9077abffaa3d4eb464ac
  summary: 8/8 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:53:25.798Z'
  generatedBy: claude
  forVersion: 5
  suggestedTestFiles:
    unit:
      - ruoyi-asset/src/test/java/com/ruoyi/asset/config/DatabaseConfigTest.java
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/config/DataSourceConfigurationTest.java
    e2e: []
  unitTestCases:
    - name: shouldHaveSqliteJdbcDriverDependency
      assertions:
        - assertNotNull(Class.forName("org.sqlite.JDBC"))
        - assertTrue(sqliteDriverAvailable)
    - name: shouldLoadDevProfileWithSqliteDataSource
      assertions:
        - >-
          assertEquals("jdbc:sqlite:data/ruoyi-asset-dev.db",
          dataSource.getUrl())
        - 'assertEquals("org.sqlite.JDBC", dataSource.getDriverClassName())'
    - name: shouldLoadProdProfileWithMysqlDataSource
      assertions:
        - 'assertTrue(dataSource.getUrl().startsWith("jdbc:mysql://"))'
        - >-
          assertEquals("com.mysql.cj.jdbc.Driver",
          dataSource.getDriverClassName())
    - name: shouldConfigureMyBatisForSqlDialectDifferences
      assertions:
        - assertNotNull(sqlSessionFactory.getConfiguration())
        - assertTrue(mybatisConfigSupportsMultipleDialects)
    - name: shouldExcludeDataDirectoryFromGit
      assertions:
        - assertTrue(gitignoreContent.contains("data/"))
        - assertFalse(gitTracksDataDirectory)
    - name: shouldHaveDataDirectoryWithGitkeep
      assertions:
        - assertTrue(Files.exists(Paths.get("data/.gitkeep")))
        - assertTrue(Files.isDirectory(Paths.get("data")))
    - name: shouldStartApplicationWithDevProfile
      assertions:
        - assertDoesNotThrow(() -> applicationContext.getBean(DataSource.class))
        - assertTrue(applicationContext.isActive())
    - name: shouldStartApplicationWithProdProfile
      assertions:
        - assertDoesNotThrow(() -> applicationContext.getBean(DataSource.class))
        - assertTrue(applicationContext.isActive())
  e2eScenarios: []
  frameworkHint: junit5-spring-boot-test
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
