---
id: core.environment-setup
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - setup
---
# Project Environment Setup

## Context

Configure complete development environment for Asset Management System with SQLite for local development.

## Acceptance Criteria

1. Create Spring Boot project structure:
   - `pom.xml` or `build.gradle` build configuration
   - `src/main/resources/application.yml` with SQLite config
   - `src/main/resources/application-dev.yml` for development overrides

2. Configure SQLite database:
   - Add SQLite dependency to build file
   - Configure SQLite datasource in application.yml
   - Set SQL dialect to SQLite
   - Enable auto-schema creation for development

3. Create main application class:
   - `src/main/java/com/ruoyi/AssetApplication.java`
   - Add @SpringBootApplication annotation
   - Enable transaction management

4. Create development documentation:
   - `README.md` with setup instructions
   - Environment variables configuration
   - Database initialization steps

5. Add development tools:
   - Database browser tool recommendation
   - API testing tool setup
   - Hot reload configuration

## Technical Notes

- Use SQLite for local development (file-based, no server required)
- JPA/Hibernate for ORM
- MyBatis-Plus for data access
- Port: 8080 (default Spring Boot)
- Database file location: `./data/asset.db`
