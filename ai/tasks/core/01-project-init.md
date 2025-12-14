---
id: core.project-init
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: [backend, setup]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Initialize ruoyi-asset Module

## Context

The asset management system is built on top of RuoYi-Vue framework. We need to create a new `ruoyi-asset` module to house all asset-related business logic.

## Acceptance Criteria

1. Create `ruoyi-asset` Maven module under `ruoyi-modules/`
2. Configure `pom.xml` with proper dependencies (ruoyi-common, ruoyi-framework)
3. Create package structure:
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
4. Add module to parent `pom.xml`
5. Create `application.yml` with MyBatis mapper scanning configuration
6. Verify module compiles without errors

## Technical Notes

- Reference: RuoYi-Vue module structure
- Pattern: Spring Boot multi-module project
