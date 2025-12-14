---
id: core.project-init
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: [infrastructure, setup]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Initialize RuoYi-Vue Project with Asset Module

## Context

The asset management system is built on top of RuoYi-Vue framework. This task sets up the base project structure and creates the `ruoyi-asset` module skeleton.

## Acceptance Criteria

1. RuoYi-Vue project is initialized with standard directory structure
2. `ruoyi-asset` module is created with proper package structure:
   - `com.ruoyi.asset.config`
   - `com.ruoyi.asset.domain.entity`
   - `com.ruoyi.asset.domain.vo`
   - `com.ruoyi.asset.domain.enums`
   - `com.ruoyi.asset.mapper`
   - `com.ruoyi.asset.service`
   - `com.ruoyi.asset.service.impl`
   - `com.ruoyi.asset.service.rule`
   - `com.ruoyi.asset.controller`
   - `com.ruoyi.asset.util`
   - `com.ruoyi.asset.constant`
3. Module is properly registered in parent pom.xml
4. Frontend `src/api/asset/` and `src/views/asset/` directories are created
5. Application starts without errors

## Technical Notes

- Reference: RuoYi-Vue official documentation
- Java version: 8+
- Spring Boot: 2.2.x
- MyBatis: 3.5.x
- **Database Configuration**:
  - Primary: SQLite (lightweight, zero-config, suitable for dev/test/small deployments)
  - Alternative: MySQL (production-ready, scalable)
  - Use Spring profiles to switch between databases (`spring.profiles.active=sqlite` or `mysql`)
  - Configure connection pool for both databases
