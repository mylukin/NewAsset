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
