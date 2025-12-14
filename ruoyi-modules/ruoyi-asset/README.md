# RuoYi Asset Module

Asset Management module for the RuoYi framework.

## Database Configuration

This module supports **dual-database infrastructure**:
- **Development**: SQLite (lightweight, no server required)
- **Production**: MySQL (enterprise-grade)

### Switching Database Profiles

#### Development Mode (SQLite)
```bash
# Uses SQLite file: data/asset-dev.db
mvn spring-boot:run -Dspring.profiles.active=dev
# Or
java -jar ruoyi-asset.jar --spring.profiles.active=dev
```

#### Production Mode (MySQL)
```bash
# Requires MySQL server running
mvn spring-boot:run -Dspring.profiles.active=prod
# Or
java -jar ruoyi-asset.jar --spring.profiles.active=prod
```

### Configuration Files

| File | Purpose |
|------|---------|
| `application.yml` | Common settings |
| `application-dev.yml` | SQLite datasource for development |
| `application-prod.yml` | MySQL datasource for production |

### MySQL Setup (Production)

1. Create database:
   ```sql
   CREATE DATABASE ruoyi_asset CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. Set environment variable for password:
   ```bash
   export DB_PASSWORD=your_secure_password
   ```

3. Run with prod profile:
   ```bash
   java -jar ruoyi-asset.jar --spring.profiles.active=prod
   ```

### SQL Dialect Handling

The module uses MyBatis `databaseIdProvider` to handle SQL dialect differences. In mapper XML files:

```xml
<!-- SQLite specific query -->
<select id="getById" databaseId="sqlite">
    SELECT * FROM asset WHERE id = #{id}
</select>

<!-- MySQL specific query -->
<select id="getById" databaseId="mysql">
    SELECT * FROM asset WHERE id = #{id} FOR UPDATE
</select>
```

### Data Directory

SQLite database files are stored in `data/` directory (gitignored).

## Data Permission Configuration

The asset module supports project-based data permission filtering.

### Data Scope Types

| Type | Value | Description |
|------|-------|-------------|
| ALL | 1 | No filtering, access all data |
| PROJECT_ONLY | 2 | Filter by user's assigned projects |
| DEPT_ONLY | 3 | Filter by user's department |
| DEPT_AND_CHILD | 4 | Filter by department and sub-departments |
| SELF_ONLY | 5 | Filter by user ID only |

### Configuration Steps

1. **Assign Projects to Users**: Insert records into `sys_user_project` table
   ```sql
   INSERT INTO sys_user_project (user_id, project_id) VALUES (1, 100);
   ```

2. **Set Role Data Scope**: Configure the role's data_scope field
   ```sql
   UPDATE sys_role SET data_scope = '2' WHERE role_id = 10;  -- PROJECT_ONLY
   ```

3. **Apply DataScope Annotation**: Use `@DataScope` in service methods
   ```java
   @DataScope(projectAlias = "a", deptAlias = "a", userAlias = "a")
   public List<Asset> selectAssetList(Asset asset) {
       return assetMapper.selectAssetList(asset);
   }
   ```

4. **Add Placeholder in Mapper XML**: Use `${params.dataScope}` in queries
   ```xml
   <select id="selectAssetList" resultMap="AssetResult">
       SELECT * FROM t_asset a
       WHERE a.del_flag = '0'
       ${params.dataScope}
   </select>
   ```

### SQL Patterns Applied

| Scope | SQL Pattern |
|-------|-------------|
| PROJECT_ONLY | `AND a.project_id IN (SELECT project_id FROM sys_user_project WHERE user_id = ?)` |
| DEPT_ONLY | `AND a.use_dept_id = ?` |
| DEPT_AND_CHILD | `AND a.use_dept_id IN (SELECT dept_id FROM sys_dept WHERE ...)` |
| SELF_ONLY | `AND a.duty_user_id = ?` |
